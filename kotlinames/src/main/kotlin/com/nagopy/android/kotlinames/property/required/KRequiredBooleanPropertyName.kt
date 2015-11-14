package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredBooleanPropertyName(val name: String) : KRequiredPropertyName<Boolean>, KSortablePropertyName<Boolean> {

    override fun name(): String = name

}
