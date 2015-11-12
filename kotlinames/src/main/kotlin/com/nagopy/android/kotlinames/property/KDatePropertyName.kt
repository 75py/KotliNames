package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName
import java.util.*

class KDatePropertyName(val name: String) : KPropertyName<Date>, KSortablePropertyName<Date> {

    override fun name(): String = name

}
