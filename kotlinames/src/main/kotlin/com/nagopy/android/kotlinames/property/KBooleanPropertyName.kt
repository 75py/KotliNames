package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KBooleanPropertyName(val name: String) : KPropertyName<Boolean>, KSortablePropertyName<Boolean> {

    override fun name(): String = name

}
