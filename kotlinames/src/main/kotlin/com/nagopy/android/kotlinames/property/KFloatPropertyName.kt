package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KFloatPropertyName(val name: String) : KPropertyName<Float>, KPropertySortable {

    override fun name(): String = name

}
