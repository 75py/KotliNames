package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KDoublePropertyName(val name: String) : KPropertyName<Double>, KSortablePropertyName<Double> {

    override fun name(): String = name

}
