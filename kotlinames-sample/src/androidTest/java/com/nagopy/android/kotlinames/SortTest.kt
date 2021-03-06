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

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nagopy.android.kotlinames.names.TestEntityNames
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SortTest {

    lateinit var context: Context

    var setupDate: Date? = null

    fun setup() {
        setupDate = Date()
        context = InstrumentationRegistry.getTargetContext()

        Realm.init(context)

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.where(TestEntity::class.java).findAll().deleteAllFromRealm()
            }
            it.executeTransaction {
                val test1 = it.createObject(TestEntity::class.java)
                test1.string = "string1"
                test1.nullString = null
                test1.emptyString = ""
                test1.b = true
                test1.by = 1
                test1.sh = 10
                test1.i = 555
                test1.l = 4
                test1.f = 5.0f
                test1.d = 6.0
                test1.date = setupDate
            }
            it.executeTransaction {
                val test2 = it.createObject(TestEntity::class.java)
                test2.string = "string2"
                test2.nullString = null
                test2.emptyString = ""
                test2.b = false
                test2.by = 1
                test2.sh = 3
                test2.i = 555
                test2.l = 5
                test2.f = 6.0f
                test2.d = 7.0
                test2.date = setupDate
            }
            it.executeTransaction {
                val test3 = it.createObject(TestEntity::class.java)
                test3.string = "string3"
                test3.nullString = null
                test3.emptyString = ""
                test3.b = false
                test3.by = 1
                test3.sh = 5
                test3.i = 555
                test3.l = 6
                test3.f = 7.0f
                test3.d = 8.0
                test3.date = setupDate
            }
        }
    }

    @Test
    fun sort() {
        setup()

        Realm.getDefaultInstance().use { realm ->
            val result = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")
                    .findAll()
            Assert.assertThat(result.size, CoreMatchers.`is`(3))
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            val sortResult1 = result.sort(TestEntityNames.string())
            Assert.assertThat(sortResult1[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(sortResult1[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult1[2].string, CoreMatchers.`is`("string3"))

            val sortResult2 = result.sort(TestEntityNames.string(), Sort.DESCENDING)
            Assert.assertThat(sortResult2[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(sortResult2[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult2[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            val sortResult3 = result.sort(TestEntityNames.by(), Sort.ASCENDING, TestEntityNames.string(), Sort.ASCENDING)
            Assert.assertThat(sortResult3[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(sortResult3[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult3[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            val sortResult4 = result.sort(TestEntityNames.by(), Sort.ASCENDING, TestEntityNames.i(), Sort.ASCENDING, TestEntityNames.sh(), Sort.ASCENDING)
            Assert.assertThat(sortResult4[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult4[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(sortResult4[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun sort_vararg() {
        setup()

        Realm.getDefaultInstance().use { realm ->
            var result = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")
                    .findAll()
            Assert.assertThat(result.size, CoreMatchers.`is`(3))
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            val sortResult1 = result.sort(TestEntityNames.string() to Sort.ASCENDING)
            Assert.assertThat(sortResult1[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(sortResult1[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult1[2].string, CoreMatchers.`is`("string3"))

            val sortResult2 = result.sort(TestEntityNames.string() to Sort.DESCENDING)
            Assert.assertThat(sortResult2[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(sortResult2[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult2[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            val sortResult3 = result.sort(TestEntityNames.by() to Sort.ASCENDING, TestEntityNames.string() to Sort.ASCENDING)
            Assert.assertThat(sortResult3[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(sortResult3[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult3[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            val sortResult4 = result.sort(TestEntityNames.by() to Sort.ASCENDING, TestEntityNames.i() to Sort.ASCENDING, TestEntityNames.sh() to Sort.ASCENDING)
            Assert.assertThat(sortResult4[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(sortResult4[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(sortResult4[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun RealmQuery_findAllSorted() {
        setup()

        Realm.getDefaultInstance().use { realm ->
            val query = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")

            var result = query.findAllSorted(TestEntityNames.string())
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result = query.findAllSorted(TestEntityNames.string(), Sort.DESCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result = query.findAllSorted(TestEntityNames.by(), Sort.ASCENDING, TestEntityNames.string(), Sort.ASCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result = query.findAllSorted(TestEntityNames.by(), Sort.ASCENDING, TestEntityNames.i(), Sort.ASCENDING, TestEntityNames.sh(), Sort.ASCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun RealmQuery_findAllSorted_vararg() {
        setup()

        Realm.getDefaultInstance().use { realm ->
            val query = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")

            var result = query.findAllSorted(TestEntityNames.string() to Sort.ASCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result = query.findAllSorted(TestEntityNames.string() to Sort.DESCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result = query.findAllSorted(TestEntityNames.by() to Sort.ASCENDING, TestEntityNames.string() to Sort.ASCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result = query.findAllSorted(TestEntityNames.by() to Sort.ASCENDING, TestEntityNames.i() to Sort.ASCENDING, TestEntityNames.sh() to Sort.ASCENDING)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }
}
