package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KDoublePropertyName(val name: String) : KPropertyName<Double>, KPropertySortable {

    override fun name(): String = name

}
