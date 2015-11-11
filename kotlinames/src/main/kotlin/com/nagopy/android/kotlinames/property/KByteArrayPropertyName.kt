package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName

class KByteArrayPropertyName(val name: String) : KPropertyName<ByteArray> {

    override fun name(): String = name

}
