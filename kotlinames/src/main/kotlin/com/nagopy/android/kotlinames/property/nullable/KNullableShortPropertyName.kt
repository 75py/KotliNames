package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableShortPropertyName(val name: String) : KNullablePropertyName<Short>, KSortablePropertyName<Short> {

    override fun name(): String = name

}
