package com.nagopy.android.kotlinames.property.nullable

import com.nagopy.android.kotlinames.KNullablePropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName
import java.util.*

class KNullableDatePropertyName(val name: String) : KNullablePropertyName<Date>, KSortablePropertyName<Date> {

    override fun name(): String = name

}
