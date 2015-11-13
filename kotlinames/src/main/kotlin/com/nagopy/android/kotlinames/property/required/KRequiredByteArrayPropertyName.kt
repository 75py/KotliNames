package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName

class KRequiredByteArrayPropertyName(val name: String) : KRequiredPropertyName<ByteArray> {

    override fun name(): String = name

}
