package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredIntegerPropertyName implements KRequiredPropertyName<Integer>, KSortablePropertyName<Integer> {

    private final String name;

    public KRequiredIntegerPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
