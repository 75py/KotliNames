package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredStringPropertyName implements KRequiredPropertyName<String>, KSortablePropertyName<String> {

    private final String name;

    public KRequiredStringPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
