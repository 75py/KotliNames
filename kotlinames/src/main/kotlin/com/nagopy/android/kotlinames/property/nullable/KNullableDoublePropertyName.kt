package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableDoublePropertyName(val name: String) : KNullablePropertyName<Double>, KSortablePropertyName<Double> {

    override fun name(): String = name

}
