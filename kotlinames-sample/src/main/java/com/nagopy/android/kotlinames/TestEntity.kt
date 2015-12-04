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
import java.util.*

@RealmClass
open class TestEntity : RealmObject() {

    open var recursive: TestEntity? = null
    open var recursiveList: RealmList<TestEntity>? = null

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

    open var byteArray: ByteArray? = null
}