package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KBytePropertyName(val name: String) : KPropertyName<Byte>, KSortablePropertyName<Byte> {

    override fun name(): String = name

}
