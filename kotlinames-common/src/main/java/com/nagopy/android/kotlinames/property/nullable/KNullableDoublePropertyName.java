package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableDoublePropertyName implements KNullablePropertyName<Double>, KSortablePropertyName<Double> {

    private final String name;

    public KNullableDoublePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
