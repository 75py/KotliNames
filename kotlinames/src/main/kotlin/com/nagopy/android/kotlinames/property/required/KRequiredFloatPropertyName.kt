package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredFloatPropertyName(val name: String) : KRequiredPropertyName<Float>, KSortablePropertyName<Float> {

    override fun name(): String = name

}
