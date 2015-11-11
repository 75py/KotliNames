package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable
import java.util.*

class KDatePropertyName(val name: String) : KPropertyName<Date>, KPropertySortable {

    override fun name(): String = name

}
