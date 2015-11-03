package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class TestEntity : RealmObject() {

    open var string: String = "defaultString"

    open var nullString: String? = null

    open var emptyString: String = ""

    open var b: Boolean = false

    open var bNullable: Boolean? = null

    open var i: Int = Int.MAX_VALUE

    open var iNullable: Int? = null

    open var l: Long = Long.MAX_VALUE

    open var lNullable: Long? = null

    open var by: Byte = Byte.MAX_VALUE

    open var byNullable: Byte? = null

    open var sh: Short = Short.MAX_VALUE

    open var shNullable: Short? = null

    open var f: Float = Float.MAX_VALUE

    open var fNullable: Float? = null

    open var d: Double = Double.MAX_VALUE

    open var dNullable: Double? = null

}