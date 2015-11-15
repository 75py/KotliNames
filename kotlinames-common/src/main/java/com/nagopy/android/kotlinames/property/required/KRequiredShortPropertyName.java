package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredShortPropertyName implements KRequiredPropertyName<Short>, KSortablePropertyName<Short> {

    private final String name;

    public KRequiredShortPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
