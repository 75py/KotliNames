/*
 * Copyright 2015 75py
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

@AutoService(Processor.class)
public class KotliNamesProcessor extends AbstractProcessor {

    static final Set<String> SUPPORTED_TYPES;

    boolean debug;

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
            note(classElement.toString());

            if (ProcessorUtil.isImplements(classElement, "io.realm.internal.RealmObjectProxy")) {
                note("Skip proxy class");
                continue;
            }

            generateNames(classElement, fields);
            generateRelationshipNames(classElement, fields);
        }
        note("End " + this.getClass().getSimpleName());
        return true;
    }

    private void generateNames(Element cls, List<VariableElement> fields) {
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

            ClassName type;
            if (typeName.equals("error.NonExistentClass")) {
                typeName = cls.getSimpleName().toString() + "RelationshipNames";
                type = ClassName.get(genPackageName, typeName);
            } else {
                if (field.asType().getKind().isPrimitive()) {
                    typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
                }
                type = createTypeClassName(field);
            }

            note("fieldName : " + fieldName);
            note("type : " + typeName);


            staticMethods.add(
                    MethodSpec.methodBuilder(fieldName)
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .returns(type)
                            .addStatement("return new $T($S)", type, fieldName)
                            .build()
            );
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

    private void generateRelationshipNames(Element cls, List<VariableElement> fields) {
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

            ClassName type;
            if (typeName.equals("error.NonExistentClass")) {
                typeName = cls.getSimpleName().toString() + "RelationshipNames";
                type = ClassName.get(genPackageName, typeName);
            } else {
                if (field.asType().getKind().isPrimitive()) {
                    typeName = PRIMITIVE_TYPE_MAP.get(typeName).getName();
                }
                type = createTypeClassName(field);
            }

            note("fieldName : " + fieldName);
            note("type : " + typeName);

            memberMethods.add(
                    MethodSpec.methodBuilder(fieldName)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(type)
                            .addStatement("return new $T(prefix + $S)", type, fieldName)
                            .build()
            );
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

            String className = "K" + (isNullable ? "Nullable" : "Required") + simpleName + "PropertyName";
            return ClassName.get(pkgName, className);
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
}
