package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KIntegerPropertyName(val name: String) : KPropertyName<Int>, KPropertySortable {

    override fun name(): String = name

}
