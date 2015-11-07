package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class TestEntity : RealmObject() {

    open var string: String = "defaultString"

    open var nullString: String? = null

    open var emptyString: String = ""

    open var b: Boolean? = false

    open var i: Int? = Int.MAX_VALUE

    open var l: Long? = Long.MAX_VALUE

    open var by: Byte? = Byte.MAX_VALUE

    open var sh: Short? = Short.MAX_VALUE

    open var f: Float? = Float.MAX_VALUE

    open var d: Double? = Double.MAX_VALUE

    open var date: Date? = Date()

    @Ignore
    open var ignoreStr: String? = null
}