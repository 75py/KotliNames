package com.nagopy.android.kotlinames

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Cat : RealmObject() {

    @Required
    open var name: String? = null

    open var weight: Double? = null

    open var children: RealmList<Cat>? = null

}
