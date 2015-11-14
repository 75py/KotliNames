package com.nagopy.android.kotlinames;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import io.realm.annotations.Ignore;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;
import io.realm.internal.RealmObjectProxy;

@AutoService(Processor.class)
public class KotliNamesProcessor extends AbstractProcessor {

    static final Set<String> SUPPORTED_TYPES;

    boolean debug = false;
    Mode mode = Mode.KOTLIN;
    static final ClassName CLASS_NAME_STRING = ClassName.get(String.class);

    static {
        Set<String> supportedTypes = new HashSet<>();
        supportedTypes.add(RealmClass.class.getName());
        SUPPORTED_TYPES = Collections.unmodifiableSet(supportedTypes);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        debug = "true".equals(processingEnv.getOptions().get("kotlinames.debug"));
        String lang = processingEnv.getOptions().get("kotlinames.lang");
        for (Mode mode : Mode.values()) {
            if (mode.value.equals(lang)) {
                this.mode = mode;
                break;
            }
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        note("debug mode : " + debug);
        note("Start " + this.getClass().getSimpleName());
        note(annotations.toString());
        Set<? extends Element> list = roundEnv.getElementsAnnotatedWith(RealmClass.class);
        for (Element classElement : list) {
            List<VariableElement> fields = new ArrayList<>();
            for (Element element : classElement.getEnclosedElements()) {
                if (element.getKind().equals(ElementKind.FIELD)) {
                    fields.add((VariableElement) element);
                }
            }
            generateNames(classElement, fields);
            generateRelationshipNames(classElement, fields);
        }
        note("End " + this.getClass().getSimpleName());
        return true;
    }

    private void generateNames(Element cls, List<VariableElement> fields) {
        note(cls.getSimpleName().toString());
        if (ProcessorUtil.isImplements(cls, RealmObjectProxy.class)) {
            note("Skip proxy class");
        } else {
            String genClassName = cls.getSimpleName().toString() + "Names";
            String targetFullClassName = cls.asType().toString();
            String targetSimpleClassName = cls.getSimpleName().toString();
            String genPackageName = targetFullClassName.substring(0, targetFullClassName.length() - targetSimpleClassName.length() - 1) + ".names";

            List<MethodSpec> staticMethods = new ArrayList<>();
            for (VariableElement field : fields) {
                String fieldName = field.getSimpleName().toString();
                String typeName = field.asType().toString();

                if (field.getAnnotation(Ignore.class) != null) {
                    note("Ignore " + fieldName);
                    continue;
                }

                if (field.asType().getKind().isPrimitive()) {
                    typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
                }

                note("fieldName : " + fieldName);
                note("type : " + typeName);

                ClassName type = createTypeClassName(field);

                if (type.equals(CLASS_NAME_STRING)) {
                    staticMethods.add(
                            MethodSpec.methodBuilder(fieldName)
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                    .returns(String.class)
                                    .addStatement("return $S", fieldName)
                                    .build()
                    );
                } else {
                    staticMethods.add(
                            MethodSpec.methodBuilder(fieldName)
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                    .returns(type)
                                    .addStatement("return new $T($S)", type, fieldName)
                                    .build()
                    );
                }
            }

            TypeSpec typeSpec = TypeSpec.classBuilder(genClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethods(staticMethods)
                    .build();

            JavaFile prefFile = JavaFile.builder(genPackageName, typeSpec)
                    .build();
            try {
                prefFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                error(e.getMessage());
            }
        }
    }

    private void generateRelationshipNames(Element cls, List<VariableElement> fields) {
        note(cls.getSimpleName().toString());
        if (ProcessorUtil.isImplements(cls, RealmObjectProxy.class)) {
            note("Skip proxy class");
        } else {
            String genClassName = cls.getSimpleName().toString() + "RelationshipNames";
            String targetFullClassName = cls.asType().toString();
            String targetSimpleClassName = cls.getSimpleName().toString();
            String genPackageName = targetFullClassName.substring(0, targetFullClassName.length() - targetSimpleClassName.length() - 1) + ".names";

            List<FieldSpec> memberFields = new ArrayList<>();
            List<MethodSpec> memberMethods = new ArrayList<>();
            for (VariableElement field : fields) {
                String fieldName = field.getSimpleName().toString();
                String typeName = field.asType().toString();

                if (field.getAnnotation(Ignore.class) != null) {
                    note("Ignore " + fieldName);
                    continue;
                }

                if (field.asType().getKind().isPrimitive()) {
                    typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
                }

                note("fieldName : " + fieldName);
                note("type : " + typeName);

                ClassName type = createTypeClassName(field);

                if (type.equals(CLASS_NAME_STRING)) {
                    memberMethods.add(
                            MethodSpec.methodBuilder(fieldName)
                                    .addModifiers(Modifier.PUBLIC)
                                    .returns(String.class)
                                    .addStatement("return prefix + $S", fieldName)
                                    .build()
                    );
                } else {
                    memberMethods.add(
                            MethodSpec.methodBuilder(fieldName)
                                    .addModifiers(Modifier.PUBLIC)
                                    .returns(type)
                                    .addStatement("return new $T(prefix + $S)", type, fieldName)
                                    .build()
                    );
                }
            }

            memberMethods.add(
                    MethodSpec.constructorBuilder()
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(String.class, "prefix")
                            .addStatement("this.prefix = prefix + $S", ".")
                            .build()
            );
            memberFields.add(FieldSpec.builder(String.class, "prefix", Modifier.PRIVATE, Modifier.FINAL).build());

            TypeSpec typeSpec = TypeSpec.classBuilder(genClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addFields(memberFields)
                    .addMethods(memberMethods)
                    .build();

            JavaFile prefFile = JavaFile.builder(genPackageName, typeSpec)
                    .build();
            try {
                prefFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                error(e.getMessage());
            }
        }
    }

    static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP;

    static {
        Map<String, Class<?>> primitiveTypeMap = new HashMap<>();
        primitiveTypeMap.put("boolean", Boolean.class);
        primitiveTypeMap.put("byte", Byte.class);
        primitiveTypeMap.put("short", Short.class);
        primitiveTypeMap.put("int", Integer.class);
        primitiveTypeMap.put("long", Long.class);
        primitiveTypeMap.put("float", Float.class);
        primitiveTypeMap.put("double", Double.class);
        PRIMITIVE_TYPE_MAP = Collections.unmodifiableMap(primitiveTypeMap);
    }

    static final Set<String> PREPARED_TYPES;

    static {
        Set<String> types = new HashSet<>();
        types.add(Boolean.class.getName());
        types.add(Byte.class.getName());
        types.add(Short.class.getName());
        types.add(Integer.class.getName());
        types.add(Long.class.getName());
        types.add(Float.class.getName());
        types.add(Double.class.getName());
        types.add(Date.class.getName());
        types.add(String.class.getName());
        types.add("ByteArray");
        PREPARED_TYPES = Collections.unmodifiableSet(types);
    }

    private void note(String message) {
        if (debug) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
        }
    }

    private void error(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
    }

    private ClassName createTypeClassName(VariableElement field) {
        String typeName = field.asType().toString();
        boolean isNullable =
                field.asType().getKind().isPrimitive()
                        || field.getAnnotation(Required.class) == null;

        if (field.asType().getKind().isPrimitive()) {
            typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
        } else if (typeName.equals("byte[]")) {
            typeName = "ByteArray";
        }

        String pkgName = "com.nagopy.android.kotlinames.property." + (isNullable ? "nullable" : "required");
        if (PREPARED_TYPES.contains(typeName)) {
            String[] wk = typeName.split("\\.");
            String simpleName = wk[wk.length - 1];

            if (mode == Mode.KOTLIN) {
                String className = "K" + (isNullable ? "Nullable" : "Required") + simpleName + "PropertyName";
                return ClassName.get(pkgName, className);
            } else {
                return CLASS_NAME_STRING;
            }
        } else if (typeName.startsWith("io.realm.RealmList<")) {
            String realmObjectName = typeName.split("<|>")[1];
            note(realmObjectName);
            String[] wk2 = realmObjectName.split("\\.");
            String realmObjectSimpleName = wk2[wk2.length - 1];
            String realmObjectPkg = realmObjectName.substring(0, realmObjectName.length() - realmObjectSimpleName.length() - 1);
            return ClassName.get(realmObjectPkg + ".names", realmObjectSimpleName + "RelationshipNames");
        } else {
            String[] wk2 = typeName.split("\\.");
            String realmObjectSimpleName = wk2[wk2.length - 1];
            String realmObjectPkg = typeName.substring(0, typeName.length() - realmObjectSimpleName.length() - 1);
            return ClassName.get(realmObjectPkg + ".names", realmObjectSimpleName + "RelationshipNames");
        }
    }

    private enum Mode {
        KOTLIN("kotlin"), JAVA("java");
        final String value;

        Mode(String value) {
            this.value = value;
        }
    }
}
