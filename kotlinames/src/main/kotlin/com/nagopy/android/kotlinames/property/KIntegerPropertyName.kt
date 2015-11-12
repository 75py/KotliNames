package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KIntegerPropertyName(val name: String) : KPropertyName<Int>, KSortablePropertyName<Int> {

    override fun name(): String = name

}
