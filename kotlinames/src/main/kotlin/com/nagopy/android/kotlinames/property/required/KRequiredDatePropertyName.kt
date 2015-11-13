package com.nagopy.android.kotlinames.property.required

import com.nagopy.android.kotlinames.KRequiredPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName
import java.util.*

class KRequiredDatePropertyName(val name: String) : KRequiredPropertyName<Date>, KSortablePropertyName<Date> {

    override fun name(): String = name

}
