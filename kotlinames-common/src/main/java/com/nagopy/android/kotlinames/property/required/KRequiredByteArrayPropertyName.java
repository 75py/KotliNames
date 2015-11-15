package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;

public class KRequiredByteArrayPropertyName implements KRequiredPropertyName<Byte[]> {

    private final String name;

    public KRequiredByteArrayPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

}
