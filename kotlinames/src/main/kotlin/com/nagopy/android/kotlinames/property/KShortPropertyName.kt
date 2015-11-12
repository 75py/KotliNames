package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KShortPropertyName(val name: String) : KPropertyName<Short>, KSortablePropertyName<Short> {

    override fun name(): String = name

}
