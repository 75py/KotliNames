package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableBytePropertyName implements KNullablePropertyName<Byte>, KSortablePropertyName<Byte> {

    private final String name;

    public KNullableBytePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
