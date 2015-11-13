package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredDoublePropertyName(val name: String) : KRequiredPropertyName<Double>, KSortablePropertyName<Double> {

    override fun name(): String = name

}
