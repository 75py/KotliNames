package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KLongPropertyName(val name: String) : KPropertyName<Long>, KSortablePropertyName<Long> {

    override fun name(): String = name

}
