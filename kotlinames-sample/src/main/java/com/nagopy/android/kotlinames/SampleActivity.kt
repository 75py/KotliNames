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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.nagopy.android.kotlinames.names.CatNames
import io.realm.Realm

class SampleActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = TextView(this)

        Realm.init(this)
        Realm.getDefaultInstance().use {
            it.where(Cat::class.java)
                    // Type safe! Cat.name can compare to String type only.
                    .equalTo(CatNames.name(), "john")

                    // This is a compile error because Cat.name is String type.
                    // .equalTo(CatNames.name, 1.5)

                    // Cat.weight is Double value
                    .greaterThan(CatNames.weight(), 5.0)

                    // This is a compile error. Cat.weight cannot compare to String value
                    // .notEqualTo(CatNames.weight, "")

                    // Relationships is also supported!
                    .equalTo(CatNames.children().name(), "tom")

                    .findAll()
        }
    }
}