package com.nagopy.android.kotlinames.property.required;

import com.nagopy.android.kotlinames.KRequiredPropertyName;
import com.nagopy.android.kotlinames.KSortablePropertyName;

import java.util.Date;

public class KRequiredDatePropertyName implements KRequiredPropertyName<Date>, KSortablePropertyName<Date> {

    private final String name;

    public KRequiredDatePropertyName(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
