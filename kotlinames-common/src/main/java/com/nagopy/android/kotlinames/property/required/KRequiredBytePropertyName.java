package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KRequiredBytePropertyName implements KRequiredPropertyName<Byte>, KSortablePropertyName<Byte> {

    private final String name;

    public KRequiredBytePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
