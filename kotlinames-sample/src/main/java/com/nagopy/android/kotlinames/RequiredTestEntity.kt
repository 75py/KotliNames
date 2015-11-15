/*
 * Copyright 2015 75py
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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