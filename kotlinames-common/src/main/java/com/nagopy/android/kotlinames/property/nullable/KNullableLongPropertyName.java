package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableLongPropertyName implements KNullablePropertyName<Long>, KSortablePropertyName<Long> {

    private final String name;

    public KNullableLongPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
