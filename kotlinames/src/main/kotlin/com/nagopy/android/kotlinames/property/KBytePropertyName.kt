package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KBytePropertyName(val name: String) : KPropertyName<Byte>, KPropertySortable {

    override fun name(): String = name

}
