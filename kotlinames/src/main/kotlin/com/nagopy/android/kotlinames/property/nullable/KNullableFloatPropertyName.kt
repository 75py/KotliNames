package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableFloatPropertyName(val name: String) : KNullablePropertyName<Float>, KSortablePropertyName<Float> {

    override fun name(): String = name

}
