package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;

public class KNullableByteArrayPropertyName implements KNullablePropertyName<Byte[]> {

    private final String name;

    public KNullableByteArrayPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

}
