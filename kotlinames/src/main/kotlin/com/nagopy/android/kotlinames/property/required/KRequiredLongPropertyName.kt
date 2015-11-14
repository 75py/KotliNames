package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredLongPropertyName(val name: String) : KRequiredPropertyName<Long>, KSortablePropertyName<Long> {

    override fun name(): String = name

}
