package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredBooleanPropertyName implements KRequiredPropertyName<Boolean>, KSortablePropertyName<Boolean> {

    private final String name;

    public KRequiredBooleanPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
