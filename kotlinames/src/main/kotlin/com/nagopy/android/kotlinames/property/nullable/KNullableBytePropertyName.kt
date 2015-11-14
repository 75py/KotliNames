package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableBytePropertyName(val name: String) : KNullablePropertyName<Byte>, KSortablePropertyName<Byte> {

    override fun name(): String = name

}
