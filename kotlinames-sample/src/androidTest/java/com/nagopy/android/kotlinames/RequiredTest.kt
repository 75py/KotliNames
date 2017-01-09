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
import com.nagopy.android.kotlinames.names.RequiredTestEntityNames
import io.realm.Case
import io.realm.Realm
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class RequiredTest {

    lateinit var context: Context

    var setupDate: Date? = null

    @Before
    fun setup() {
        setupDate = Date()
        context = InstrumentationRegistry.getTargetContext()

        Realm.init(context)

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.where(RequiredTestEntity::class.java).findAll().deleteAllFromRealm()
            }
            it.executeTransaction {
                val test1 = it.createObject(RequiredTestEntity::class.java)
                test1.string = "string"
                test1.emptyString = ""
                test1.b = true
                test1.by = 1
                test1.sh = 2
                test1.i = 3
                test1.l = 4
                test1.f = 5.0f
                test1.d = 6.0
                test1.date = setupDate
            }
            it.executeTransaction {
                val test2 = it.createObject(RequiredTestEntity::class.java)
                test2.string = "string2"
                test2.emptyString = ""
                test2.b = false
                test2.by = 2
                test2.sh = 3
                test2.i = 4
                test2.l = 5
                test2.f = 6.0f
                test2.d = 7.0
                test2.date = setupDate
            }
        }
    }

    @Test
    fun average_byte() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.by())
            Assert.assertThat(avg, CoreMatchers.`is`(1.5))
        }
    }

    @Test
    fun average_short() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.sh())
            Assert.assertThat(avg, CoreMatchers.`is`(2.5))
        }
    }

    @Test
    fun average_int() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.i())
            Assert.assertThat(avg, CoreMatchers.`is`(3.5))
        }
    }

    @Test
    fun average_long() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.l())
            Assert.assertThat(avg, CoreMatchers.`is`(4.5))
        }
    }

    @Test
    fun average_float() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.f())
            Assert.assertThat(avg, CoreMatchers.`is`(5.5))
        }
    }

    @Test
    fun average_double() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).average(RequiredTestEntityNames.d())
            Assert.assertThat(avg, CoreMatchers.`is`(6.5))
        }
    }

    @Test
    fun beginWith() {
        val map = hashMapOf(
                //                null to 2L,
                "str" to 2L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .beginsWith(RequiredTestEntityNames.string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun beginWith_case() {
        val map = hashMapOf(
                //                null to 2L,
                "str" to 2L,
                "Str" to 2L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .beginsWith(RequiredTestEntityNames.string(), it.key, Case.INSENSITIVE)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun between_date() {
        Realm.getDefaultInstance().use {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 1)
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.date(), calendar.time, calendar.time).count()
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test
    fun between_byte() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.by(), 2, 3).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun between_short() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.sh(), 3, 4).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun between_int() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.i(), 4, 5).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun between_long() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.l(), 5, 6).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun between_float() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.f(), 6f, 7f).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun between_double() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.d(), 7.0, 8.0).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun contains() {
        val map = hashMapOf(
                //                null to 2L,
                "str" to 2L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .contains(RequiredTestEntityNames.string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun contains_case() {
        val map = hashMapOf(
                //                null to 2L,
                "str" to 2L,
                "Str" to 2L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .contains(RequiredTestEntityNames.string(), it.key, Case.INSENSITIVE)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith() {
        val map = hashMapOf(
                //                null to 2L,
                "string" to 1L,
                "ING" to 0L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .endsWith(RequiredTestEntityNames.string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith_case() {
        val map = hashMapOf(
                //                null to 2L,
                "string" to 1L,
                "ING" to 1L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .endsWith(RequiredTestEntityNames.string(), it.key, Case.INSENSITIVE)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_string() {
        val map = hashMapOf(
                //                null to 0L,
                "string" to 1L,
                "String" to 0L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_string_ignore_case() {
        val map = hashMapOf(
                //                null to 0L,
                "string" to 1L,
                "String" to 1L,
                "test" to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.string(), it.key, Case.INSENSITIVE)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_boolean() {
        val map = hashMapOf(
                //                null to 0L,
                true to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.b(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_byte() {
        val map = hashMapOf(
                //                null to 0L,
                1.toByte() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_short() {
        val map = hashMapOf(
                //                null to 0L,
                2.toShort() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_int() {
        val map = hashMapOf(
                //                null to 0L,
                3 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_long() {
        val map = hashMapOf(
                //                null to 0L,
                4L to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_float() {
        val map = hashMapOf(
                //                null to 0L,
                5f to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_double() {
        val map = hashMapOf(
                //                null to 0L,
                6.0 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_date() {
        val map = hashMapOf(
                //                null to 0L,
                setupDate!! to 2L,
                Date() to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_date() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val map = hashMapOf(
                cal.time to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_byte() {
        val map = hashMapOf(
                0.toByte() to 2L,
                2.toByte() to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_short() {
        val map = hashMapOf(
                0.toShort() to 2L,
                3.toShort() to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun greaterThan_int() {
        val map = hashMapOf(
                0 to 2L,
                4 to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_long() {
        val map = hashMapOf(
                0L to 2L,
                5L to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_float() {
        val map = hashMapOf(
                0f to 2L,
                6f to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_double() {
        val map = hashMapOf(
                0.0 to 2L,
                7.0 to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun greaterThanOrEqualTo_date() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val map = hashMapOf(
                cal.time to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_byte() {
        val map = hashMapOf(
                0.toByte() to 2L,
                2.toByte() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_short() {
        val map = hashMapOf(
                0.toShort() to 2L,
                3.toShort() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun greaterThanOrEqualTo_int() {
        val map = hashMapOf(
                0 to 2L,
                4 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_long() {
        val map = hashMapOf(
                0L to 2L,
                5L to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_float() {
        val map = hashMapOf(
                0f to 2L,
                6f to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_double() {
        val map = hashMapOf(
                0.0 to 2L,
                7.0 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun isEmpty() {
        Realm.getDefaultInstance().use { realm ->
            val count = realm.where(RequiredTestEntity::class.java)
                    .isEmpty(RequiredTestEntityNames.emptyString())
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(2L))
        }
    }

    //    @Test
    //    fun isNull() {
    //        Realm.getDefaultInstance().use { realm ->
    //            val count = realm.where(RequiredTestEntity::class.java)
    //                    .isNull(RequiredTestEntityNames.string())
    //                    .count()
    //            Assert.assertThat(count, CoreMatchers.`is`(2L))
    //        }
    //    }
    //
    //    @Test
    //    fun isNotNull() {
    //        Realm.getDefaultInstance().use { realm ->
    //            val count = realm.where(RequiredTestEntity::class.java)
    //                    .isNotNull(RequiredTestEntityNames.string())
    //                    .count()
    //            Assert.assertThat(count, CoreMatchers.`is`(0L))
    //        }
    //    }

    @Test
    fun lessThan_date() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val map = hashMapOf(
                cal.time to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThan_byte() {
        val map = hashMapOf(
                0.toByte() to 0L,
                2.toByte() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThan_short() {
        val map = hashMapOf(
                0.toShort() to 0L,
                3.toShort() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun lessThan_int() {
        val map = hashMapOf(
                0 to 0L,
                4 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThan_long() {
        val map = hashMapOf(
                0L to 0L,
                5L to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThan_float() {
        val map = hashMapOf(
                0f to 0L,
                6f to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThan_double() {
        val map = hashMapOf(
                0.0 to 0L,
                7.0 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun lessThanOrEqualTo_date() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val map = hashMapOf(
                cal.time to 0L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_byte() {
        val map = hashMapOf(
                0.toByte() to 0L,
                2.toByte() to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_short() {
        val map = hashMapOf(
                0.toShort() to 0L,
                3.toShort() to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun lessThanOrEqualTo_int() {
        val map = hashMapOf(
                0 to 0L,
                4 to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_long() {
        val map = hashMapOf(
                0L to 0L,
                5L to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_float() {
        val map = hashMapOf(
                0f to 0L,
                6f to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_double() {
        val map = hashMapOf(
                0.0 to 0L,
                7.0 to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun maximumDate() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).maximumDate(RequiredTestEntityNames.date())
            Assert.assertThat(max, CoreMatchers.notNullValue())
        }
    }

    @Test
    fun max_byte() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.by())
            Assert.assertThat(max.toByte(), CoreMatchers.`is`(2.toByte()))
        }
    }

    @Test
    fun max_short() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.sh())
            Assert.assertThat(max.toShort(), CoreMatchers.`is`(3.toShort()))
        }
    }

    @Test
    fun max_int() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.i())
            Assert.assertThat(max.toInt(), CoreMatchers.`is`(4.toInt()))
        }
    }

    @Test
    fun max_long() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.l())
            Assert.assertThat(max.toLong(), CoreMatchers.`is`(5.toLong()))
        }
    }

    @Test
    fun max_float() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.f())
            Assert.assertThat(max.toFloat(), CoreMatchers.`is`(6.toFloat()))
        }
    }

    @Test
    fun max_double() {
        Realm.getDefaultInstance().use {
            val max = it.where(RequiredTestEntity::class.java).max(RequiredTestEntityNames.d())
            Assert.assertThat(max.toDouble(), CoreMatchers.`is`(7.toDouble()))
        }
    }


    @Test
    fun minimumDate() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).minimumDate(RequiredTestEntityNames.date())
            Assert.assertThat(min, CoreMatchers.notNullValue())
        }
    }

    @Test
    fun min_byte() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.by())
            Assert.assertThat(min.toByte(), CoreMatchers.`is`(1.toByte()))
        }
    }

    @Test
    fun min_short() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.sh())
            Assert.assertThat(min.toShort(), CoreMatchers.`is`(2.toShort()))
        }
    }

    @Test
    fun min_int() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.i())
            Assert.assertThat(min.toInt(), CoreMatchers.`is`(3.toInt()))
        }
    }

    @Test
    fun min_long() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.l())
            Assert.assertThat(min.toLong(), CoreMatchers.`is`(4.toLong()))
        }
    }

    @Test
    fun min_float() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.f())
            Assert.assertThat(min.toFloat(), CoreMatchers.`is`(5.toFloat()))
        }
    }

    @Test
    fun min_double() {
        Realm.getDefaultInstance().use {
            val min = it.where(RequiredTestEntity::class.java).min(RequiredTestEntityNames.d())
            Assert.assertThat(min.toDouble(), CoreMatchers.`is`(6.toDouble()))
        }
    }


    @Test
    fun notEqualTo_string() {
        val map = hashMapOf(
                //                null to 2L,
                "string" to 1L,
                "String" to 2L,
                "test" to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_string_ignore_case() {
        val map = hashMapOf(
                //                null to 2L,
                "string" to 1L,
                "String" to 1L,
                "test" to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.string(), it.key, Case.INSENSITIVE)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_boolean() {
        val map = hashMapOf(
                //                null to 2L,
                true to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.b(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_byte() {
        val map = hashMapOf(
                //                null to 2L,
                1.toByte() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_short() {
        val map = hashMapOf(
                //                null to 2L,
                2.toShort() to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_int() {
        val map = hashMapOf(
                //                null to 2L,
                3 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_long() {
        val map = hashMapOf(
                //                null to 2L,
                4L to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_float() {
        val map = hashMapOf(
                //                null to 2L,
                5f to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_double() {
        val map = hashMapOf(
                //                null to 2L,
                6.0 to 1L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_date() {
        val map = hashMapOf(
                //                null to 2L,
                setupDate!! to 0L,
                Date() to 2L
        )
        Realm.getDefaultInstance().use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun sum_byte() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.by())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(3.0))
        }
    }

    @Test
    fun sum_short() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.sh())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(5.0))
        }
    }

    @Test
    fun sum_int() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.i())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(7.0))
        }
    }

    @Test
    fun sum_long() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.l())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(9.0))
        }
    }

    @Test
    fun sum_float() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.f())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(11.0))
        }
    }

    @Test
    fun sum_double() {
        Realm.getDefaultInstance().use {
            val avg = it.where(RequiredTestEntity::class.java).sum(RequiredTestEntityNames.d())
            Assert.assertThat(avg.toDouble(), CoreMatchers.`is`(13.0))
        }
    }

    /*
    @Test(expected = IllegalArgumentException::class)
    fun equalTo_string_number() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java)
                    .equalTo("string", 1)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_string_boolean() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java)
                    .equalTo("string", false)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test
    fun equalTo_boolean() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java)
                    .equalTo(RequiredTestEntityNames.b(), false) // TypeSafe!
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_boolean_number() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java)
                    .equalTo("b", 1)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_boolean_string() {
        Realm.getDefaultInstance().use {
            val count = it.where(RequiredTestEntity::class.java)
                    .equalTo("b", "false")
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }
    */
}