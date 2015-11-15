package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableFloatPropertyName implements KNullablePropertyName<Float>, KSortablePropertyName<Float> {

    private final String name;

    public KNullableFloatPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
