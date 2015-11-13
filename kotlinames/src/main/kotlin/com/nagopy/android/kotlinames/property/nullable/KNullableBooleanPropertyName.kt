package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableBooleanPropertyName(val name: String) : KNullablePropertyName<Boolean>, KSortablePropertyName<Boolean> {

    override fun name(): String = name

}
