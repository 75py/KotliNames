package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredIntegerPropertyName(val name: String) : KRequiredPropertyName<Int>, KSortablePropertyName<Int> {

    override fun name(): String = name

}
