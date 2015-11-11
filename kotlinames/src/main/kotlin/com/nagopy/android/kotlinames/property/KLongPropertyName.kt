package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KLongPropertyName(val name: String) : KPropertyName<Long>, KPropertySortable {

    override fun name(): String = name

}
