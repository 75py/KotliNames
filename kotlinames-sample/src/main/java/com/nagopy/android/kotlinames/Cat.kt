package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Cat : RealmObject(){

    open var name: String? = null

    open var weight : Double? = null

}
