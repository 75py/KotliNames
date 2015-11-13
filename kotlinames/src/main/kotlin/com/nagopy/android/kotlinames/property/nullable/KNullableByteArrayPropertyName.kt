package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName

class KNullableByteArrayPropertyName(val name: String) : KNullablePropertyName<ByteArray> {

    override fun name(): String = name

}
