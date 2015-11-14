package com.nagopy.android.kotlinames

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

@RealmClass
open class RequiredTestEntity : RealmObject() {

    open var recursive: RequiredTestEntity? = null

    open var recursiveList: RealmList<RequiredTestEntity>? = null

    @Required
    open var string: String = "defaultString"

    @Required
    open var emptyString: String = ""

    @Required
    open var b: Boolean? = false

    @Required
    open var i: Int? = Int.MAX_VALUE

    @Required
    open var l: Long? = Long.MAX_VALUE

    @Required
    open var by: Byte? = Byte.MAX_VALUE

    @Required
    open var sh: Short? = Short.MAX_VALUE

    @Required
    open var f: Float? = Float.MAX_VALUE

    @Required
    open var d: Double? = Double.MAX_VALUE

    @Required
    open var date: Date? = Date()

    @Ignore
    open var ignoreStr: String? = null

    @Required
    open var byteArray: ByteArray? = null
}