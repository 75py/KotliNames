package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableIntegerPropertyName(val name: String) : KNullablePropertyName<Int>, KSortablePropertyName<Int> {

    override fun name(): String = name

}
