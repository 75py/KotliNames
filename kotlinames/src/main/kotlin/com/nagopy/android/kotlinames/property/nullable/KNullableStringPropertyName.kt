package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KNullableStringPropertyName(val name: String) : KNullablePropertyName<String>, KSortablePropertyName<String> {

    override fun name(): String = name

}
