package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

public class KNullableStringPropertyName implements KNullablePropertyName<String>, KSortablePropertyName<String> {

    private final String name;

    public KNullableStringPropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
