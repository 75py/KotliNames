package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KSortablePropertyName

class KStringPropertyName(val name: String) : KPropertyName<String>, KSortablePropertyName<String> {

    override fun name(): String = name

}
