package com.nagopy.android.kotlinames;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import io.realm.annotations.RealmClass;
import io.realm.internal.RealmObjectProxy;

@AutoService(Processor.class)
public class KotliNamesProcessor extends AbstractProcessor {

    static final Set<String> SUPPORTED_TYPES;

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
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
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
        }
        note("End " + this.getClass().getSimpleName());
        return true;
    }

    private void generateNames(Element cls, List<VariableElement> fields) {
        note(cls.getSimpleName().toString());
        if (ProcessorUtil.isImplements(cls, RealmObjectProxy.class)) {
            note("Skip proxy class");
        } else {
            List<FieldSpec> fieldSpecs = new ArrayList<>();
            for (VariableElement field : fields) {
                String fieldName = field.getSimpleName().toString();
                String typeName = field.asType().toString();

                if (field.asType().getKind().isPrimitive()) {
                    typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
                }

                note("fieldName : " + fieldName);
                note("type : " + typeName);

                ParameterizedTypeName type = ParameterizedTypeName.get(
                        ClassName.get(KPropertyName.class),
                        TypeVariableName.get(typeName)
                );

                FieldSpec fieldSpec = FieldSpec.builder(type, fieldName, Modifier.PUBLIC)
                        .addModifiers(Modifier.STATIC, Modifier.FINAL)
                        .initializer(CodeBlock.builder()
                                .beginControlFlow("new $T()", type)
                                .add(MethodSpec.methodBuilder("name")
                                        .addAnnotation(Override.class)
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(String.class)
                                        .addStatement("return toString()")
                                        .build().toString())
                                .add(MethodSpec.methodBuilder("toString")
                                        .addAnnotation(Override.class)
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(String.class)
                                        .addStatement("return $S", fieldName)
                                        .build().toString())
                                .endControlFlow()
                                .build())
                        .build();

                fieldSpecs.add(fieldSpec);
            }

            String genClassName = cls.getSimpleName().toString() + "Names";
            String targetFullClassName = cls.asType().toString();
            String targetSimpleClassName = cls.getSimpleName().toString();
            String genPackageName = targetFullClassName.substring(0, targetFullClassName.length() - targetSimpleClassName.length() - 1) + ".names";
            TypeSpec typeSpec = TypeSpec.classBuilder(genClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addFields(fieldSpecs)
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

    private void note(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }

    private void error(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
    }
}
