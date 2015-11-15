package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredFloatPropertyName implements KRequiredPropertyName<Float>, KSortablePropertyName<Float> {

    private final String name;

    public KRequiredFloatPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
