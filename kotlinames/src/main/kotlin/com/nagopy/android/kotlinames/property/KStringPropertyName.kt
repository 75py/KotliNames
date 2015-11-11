package com.nagopy.android.kotlinames.property

import com.nagopy.android.kotlinames.KPropertyName
import com.nagopy.android.kotlinames.KPropertySortable

class KStringPropertyName(val name: String) : KPropertyName<String>, KPropertySortable {

    override fun name(): String = name

}
