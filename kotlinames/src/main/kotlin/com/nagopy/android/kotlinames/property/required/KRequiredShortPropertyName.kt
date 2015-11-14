package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredShortPropertyName(val name: String) : KRequiredPropertyName<Short>, KSortablePropertyName<Short> {

    override fun name(): String = name

}
