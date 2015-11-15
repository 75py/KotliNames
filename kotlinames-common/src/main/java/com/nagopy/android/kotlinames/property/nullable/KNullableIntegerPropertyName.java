package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableIntegerPropertyName implements KNullablePropertyName<Integer>, KSortablePropertyName<Integer> {

    private final String name;

    public KNullableIntegerPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
