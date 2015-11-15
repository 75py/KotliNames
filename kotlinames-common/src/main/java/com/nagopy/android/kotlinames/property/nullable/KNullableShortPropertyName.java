package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableShortPropertyName implements KNullablePropertyName<Short>, KSortablePropertyName<Short> {

    private final String name;

    public KNullableShortPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
