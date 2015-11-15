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
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class RelationshipTest {

    lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        Realm.deleteRealm(RealmConfiguration.Builder(context).build())

        Realm.getInstance(context).use {
            it.executeTransaction {
                it.where(TestEntity::class.java).findAll().clear()
            }
            it.executeTransaction {
                val test1 = it.createObject(TestEntity::class.java)
                test1.string = "string"
                test1.nullString = null
                test1.emptyString = ""
                test1.b = true
                test1.by = 1
                test1.sh = 2
                test1.i = 3
                test1.l = 4
                test1.f = 5.0f
                test1.d = 6.0
                test1.date = Date()

                val test2 = it.createObject(TestEntity::class.java)
                test2.string = "string2"
                test2.nullString = null
                test2.emptyString = ""
                test2.b = false
                test2.by = 2
                test2.sh = 3
                test2.i = 4
                test2.l = 5
                test2.f = 6.0f
                test2.d = 7.0
                test2.date = Date()

                val test3 = it.createObject(TestEntity::class.java)
                test3.string = "parent"
                test3.nullString = "parent"
                test3.emptyString = "parent"
                test3.b = null
                test3.by = null
                test3.sh = null
                test3.i = null
                test3.l = null
                test3.f = null
                test3.d = null
                test3.date = null
                test3.recursive = test1
                test3.recursiveList!!.add(test1)
                test3.recursiveList!!.add(test2)
            }
        }
    }

    @Test
    fun beginWith() {
        val map = hashMapOf(
                null to 1L,
                "str" to 1L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .beginsWith(TestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun beginWith_case() {
        val map = hashMapOf(
                null to 1L,
                "str" to 1L,
                "Str" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .beginsWith(TestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    // Realm 0.84.2
    // java.lang.IllegalArgumentException: Illegal Argument: between() does not support queries using child object fields.
    @Test(expected = IllegalArgumentException::class)
    fun between_date() {
        Realm.getInstance(context).use {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 1)
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().date(), calendar.time, calendar.time).count()
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_byte() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().by(), 2, 3).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_short() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().sh(), 3, 4).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_int() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().i(), 4, 5).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_long() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().l(), 5, 6).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_float() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().f(), 6f, 7f).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_double() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java).between(TestEntityNames.recursiveList().d(), 7.0, 8.0).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun contains() {
        val map = hashMapOf(
                null to 1L,
                "str" to 1L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .contains(TestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun contains_case() {
        val map = hashMapOf(
                null to 1L,
                "str" to 1L,
                "Str" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .contains(TestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith() {
        val map = hashMapOf(
                null to 1L,
                "string" to 1L,
                "ING" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .endsWith(TestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith_case() {
        val map = hashMapOf(
                null to 1L,
                "string" to 1L,
                "ING" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .endsWith(TestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_string() {
        val map = hashMapOf(
                null to 0L,
                "string" to 1L,
                "String" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_string_ignore_case() {
        val map = hashMapOf(
                null to 0L,
                "string" to 1L,
                "String" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_boolean() {
        val map = hashMapOf(
                null to 0L,
                true to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().b(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_byte() {
        val map = hashMapOf(
                null to 0L,
                1.toByte() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_short() {
        val map = hashMapOf(
                null to 0L,
                2.toShort() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_int() {
        val map = hashMapOf(
                null to 0L,
                3 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_long() {
        val map = hashMapOf(
                null to 0L,
                4L to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_float() {
        val map = hashMapOf(
                null to 0L,
                5f to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_double() {
        val map = hashMapOf(
                null to 0L,
                6.0 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_date() {
        val map = hashMapOf(
                null to 0L,
                Date() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .equalTo(TestEntityNames.recursiveList().date(), it.key)
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
                cal.time to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_byte() {
        val map = hashMapOf(
                0.toByte() to 1L,
                2.toByte() to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_short() {
        val map = hashMapOf(
                0.toShort() to 1L,
                3.toShort() to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun greaterThan_int() {
        val map = hashMapOf(
                0 to 1L,
                4 to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_long() {
        val map = hashMapOf(
                0L to 1L,
                5L to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_float() {
        val map = hashMapOf(
                0f to 1L,
                6f to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThan_double() {
        val map = hashMapOf(
                0.0 to 1L,
                7.0 to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThan(TestEntityNames.recursiveList().d(), it.key)
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
                cal.time to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_byte() {
        val map = hashMapOf(
                0.toByte() to 1L,
                2.toByte() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_short() {
        val map = hashMapOf(
                0.toShort() to 1L,
                3.toShort() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun greaterThanOrEqualTo_int() {
        val map = hashMapOf(
                0 to 1L,
                4 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_long() {
        val map = hashMapOf(
                0L to 1L,
                5L to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_float() {
        val map = hashMapOf(
                0f to 1L,
                6f to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun greaterThanOrEqualTo_double() {
        val map = hashMapOf(
                0.0 to 1L,
                7.0 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .greaterThanOrEqualTo(TestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun isEmpty() {
        Realm.getInstance(context).use { realm ->
            val count = realm.where(TestEntity::class.java)
                    .isEmpty(TestEntityNames.recursiveList().emptyString())
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun isNull() {
        Realm.getInstance(context).use { realm ->
            val count = realm.where(TestEntity::class.java)
                    .isNull(TestEntityNames.recursiveList().nullString())
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun isNotNull() {
        Realm.getInstance(context).use { realm ->
            val count = realm.where(TestEntity::class.java)
                    .isNotNull(TestEntityNames.recursiveList().nullString())
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test
    fun lessThan_date() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val map = hashMapOf(
                cal.time to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().date(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().by(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().sh(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().i(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().l(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().f(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThan(TestEntityNames.recursiveList().d(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_byte() {
        val map = hashMapOf(
                0.toByte() to 0L,
                2.toByte() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_short() {
        val map = hashMapOf(
                0.toShort() to 0L,
                3.toShort() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }


    @Test
    fun lessThanOrEqualTo_int() {
        val map = hashMapOf(
                0 to 0L,
                4 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_long() {
        val map = hashMapOf(
                0L to 0L,
                5L to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_float() {
        val map = hashMapOf(
                0f to 0L,
                6f to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun lessThanOrEqualTo_double() {
        val map = hashMapOf(
                0.0 to 0L,
                7.0 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .lessThanOrEqualTo(TestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_string() {
        val map = hashMapOf(
                null to 1L,
                "string" to 1L,
                "String" to 1L,
                "test" to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    // Realm 0.84.2
    // java.lang.IllegalArgumentException: Link queries cannot be case insensitive - coming soon.
    @Test(expected = IllegalArgumentException::class)
    fun notEqualTo_string_ignore_case() {
        val map = hashMapOf(
                null to 1L,
                "string" to 1L,
                "String" to 1L,
                "test" to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_boolean() {
        val map = hashMapOf(
                null to 1L,
                true to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().b(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_byte() {
        val map = hashMapOf(
                null to 1L,
                1.toByte() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_short() {
        val map = hashMapOf(
                null to 1L,
                2.toShort() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_int() {
        val map = hashMapOf(
                null to 1L,
                3 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_long() {
        val map = hashMapOf(
                null to 1L,
                4L to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_float() {
        val map = hashMapOf(
                null to 1L,
                5f to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_double() {
        val map = hashMapOf(
                null to 1L,
                6.0 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_date() {
        val map = hashMapOf(
                null to 1L,
                Date() to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(TestEntity::class.java)
                        .notEqualTo(TestEntityNames.recursiveList().date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }
}
