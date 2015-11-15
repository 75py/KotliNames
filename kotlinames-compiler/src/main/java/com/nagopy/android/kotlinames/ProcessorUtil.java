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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleElementVisitor7;
import javax.lang.model.util.SimpleTypeVisitor7;

public class ProcessorUtil {

    private ProcessorUtil() {
        throw new AssertionError();
    }

    private static Set<String> getInterfaces(final Element element) {
        final Set<String> interfaces = new HashSet<>();
        if (element != null) element.accept(new SimpleElementVisitor7<Void, Void>() {
            @Override
            public Void visitType(final TypeElement e, final Void p) {
                interfaces.addAll(getInterfaces(e.getInterfaces()));
                TypeMirror superClass = e.getSuperclass();
                if (superClass.getKind().equals(TypeKind.NONE)) {
                    return null;
                }
                return superClass.accept(new SimpleTypeVisitor7<Void, Void>() {
                    @Override
                    public Void visitDeclared(final DeclaredType t, final Void p) {
                        interfaces.addAll(getInterfaces(t.asElement()));
                        return null;
                    }
                }, null);
            }
        }, null);
        return interfaces;
    }

    private static Set<String> getInterfaces(final List<? extends TypeMirror> typeMirrors) {
        Set<String> interfaces = new HashSet<>();
        for (final TypeMirror typeMirror : typeMirrors) {
            interfaces.add(typeMirror.toString());
        }
        return interfaces;
    }

    public static boolean isImplements(final Element element, final Class<?> ifClass) {
        return isImplements(element, ifClass.getName());
    }

    public static boolean isImplements(final Element element, final String interfaceFullName) {
        final Set<String> interfaces = getInterfaces(element);
        return interfaces.contains(interfaceFullName);
    }
}
