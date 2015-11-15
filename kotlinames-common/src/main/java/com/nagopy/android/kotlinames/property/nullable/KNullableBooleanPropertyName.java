package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableBooleanPropertyName implements KNullablePropertyName<Boolean>, KSortablePropertyName<Boolean> {

    private final String name;

    public KNullableBooleanPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
