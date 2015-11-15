package com.nagopy.android.kotlinames.property.nullable;

import com.nagopy.android.kotlinames.KNullablePropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

import java.util.Date;

public class KNullableDatePropertyName implements KNullablePropertyName<Date>, KSortablePropertyName<Date> {

    private final String name;

    public KNullableDatePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
