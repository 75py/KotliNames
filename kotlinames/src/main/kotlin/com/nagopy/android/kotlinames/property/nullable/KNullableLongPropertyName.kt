package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableLongPropertyName(val name: String) : KNullablePropertyName<Long>, KSortablePropertyName<Long> {

    override fun name(): String = name

}
