package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredDoublePropertyName implements KRequiredPropertyName<Double>, KSortablePropertyName<Double> {

    private final String name;

    public KRequiredDoublePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
