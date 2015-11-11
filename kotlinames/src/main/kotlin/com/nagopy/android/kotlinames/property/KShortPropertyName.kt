package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KShortPropertyName(val name: String) : KPropertyName<Short>, KPropertySortable {

    override fun name(): String = name

}
