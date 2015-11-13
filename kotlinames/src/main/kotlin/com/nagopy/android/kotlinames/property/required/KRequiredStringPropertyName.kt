package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KRequiredStringPropertyName(val name: String) : KRequiredPropertyName<String>, KSortablePropertyName<String> {

    override fun name(): String = name

}
