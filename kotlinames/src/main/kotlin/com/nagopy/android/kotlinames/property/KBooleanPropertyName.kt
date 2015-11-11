package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KBooleanPropertyName(val name: String) : KPropertyName<Boolean>, KPropertySortable {

    override fun name(): String = name

}
