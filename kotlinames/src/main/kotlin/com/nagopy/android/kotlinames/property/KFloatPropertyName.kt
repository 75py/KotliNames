package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KFloatPropertyName(val name: String) : KPropertyName<Float>, KSortablePropertyName<Float> {

    override fun name(): String = name

}
