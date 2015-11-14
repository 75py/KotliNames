package com.nagopy.android.kotlinames

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nagopy.android.kotlinames.names.RequiredTestEntityNames
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class RelationshipRequiredTest {

    lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        Realm.deleteRealm(RealmConfiguration.Builder(context).build())

        Realm.getInstance(context).use {
            it.executeTransaction {
                it.where(RequiredTestEntity::class.java).findAll().clear()
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
                test1.date = Date()
                test1.recursive = null
                test1.recursiveList = null

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
                test2.date = Date()
                test2.recursive = null
                test2.recursiveList = null

                val test3 = it.createObject(RequiredTestEntity::class.java)
                test3.string = "parent"
                test3.emptyString = "parent"
                test3.recursive = test1
                test3.recursiveList = RealmList(test1, test2)
            }
        }
    }

    @Test
    fun beginWith() {
        val map = hashMapOf(
                //                null to 1L,
                "str" to 1L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .beginsWith(RequiredTestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun beginWith_case() {
        val map = hashMapOf(
                //                null to 1L,
                "str" to 1L,
                "Str" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .beginsWith(RequiredTestEntityNames.recursiveList().string(), it.key, false)
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
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().date(), calendar.time, calendar.time).count()
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_byte() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().by(), 2, 3).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_short() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().sh(), 3, 4).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_int() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().i(), 4, 5).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_long() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().l(), 5, 6).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_float() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().f(), 6f, 7f).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun between_double() {
        Realm.getInstance(context).use {
            val count = it.where(RequiredTestEntity::class.java).between(RequiredTestEntityNames.recursiveList().d(), 7.0, 8.0).count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test
    fun contains() {
        val map = hashMapOf(
                //                null to 1L,
                "str" to 1L,
                "Str" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .contains(RequiredTestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun contains_case() {
        val map = hashMapOf(
                //                null to 1L,
                "str" to 1L,
                "Str" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .contains(RequiredTestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith() {
        val map = hashMapOf(
                //                null to 1L,
                "string" to 1L,
                "ING" to 0L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .endsWith(RequiredTestEntityNames.recursiveList().string(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun endsWith_case() {
        val map = hashMapOf(
                //                null to 1L,
                "string" to 1L,
                "ING" to 1L,
                "test" to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .endsWith(RequiredTestEntityNames.recursiveList().string(), it.key, false)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().string(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().string(), it.key, false)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().b(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().by(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().sh(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().i(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().l(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().f(), it.key)
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun equalTo_date() {
        val map = hashMapOf(
                //                null to 0L,
                Date() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .equalTo(RequiredTestEntityNames.recursiveList().date(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().date(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().by(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().sh(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().i(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().l(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().f(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThan(RequiredTestEntityNames.recursiveList().d(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().date(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().by(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().sh(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().i(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().l(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().f(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .greaterThanOrEqualTo(RequiredTestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun isEmpty() {
        Realm.getInstance(context).use { realm ->
            val count = realm.where(RequiredTestEntity::class.java)
                    .isEmpty(RequiredTestEntityNames.recursiveList().emptyString())
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    //    @Test
    //    fun isNull() {
    //        Realm.getInstance(context).use { realm ->
    //            val count = realm.where(RequiredTestEntity::class.java)
    //                    .isNull(RequiredTestEntityNames.recursiveList().nullString())
    //                    .count()
    //            Assert.assertThat(count, CoreMatchers.`is`(1L))
    //        }
    //    }
    //
    //    @Test
    //    fun isNotNull() {
    //        Realm.getInstance(context).use { realm ->
    //            val count = realm.where(RequiredTestEntity::class.java)
    //                    .isNotNull(RequiredTestEntityNames.recursiveList().nullString())
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
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().date(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().by(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().sh(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().i(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().l(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().f(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThan(RequiredTestEntityNames.recursiveList().d(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().date(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().by(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().sh(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().i(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().l(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().f(), it.key)
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
                val count = realm.where(RequiredTestEntity::class.java)
                        .lessThanOrEqualTo(RequiredTestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_string() {
        val map = hashMapOf(
                //                null to 1L,
                "string" to 1L,
                "String" to 1L,
                "test" to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().string(), it.key)
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
                //                null to 1L,
                "string" to 1L,
                "String" to 1L,
                "test" to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().string(), it.key, false)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_boolean() {
        val map = hashMapOf(
                //                null to 1L,
                true to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().b(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_byte() {
        val map = hashMapOf(
                //                null to 1L,
                1.toByte() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().by(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_short() {
        val map = hashMapOf(
                //                null to 1L,
                2.toShort() to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().sh(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_int() {
        val map = hashMapOf(
                //                null to 1L,
                3 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().i(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_long() {
        val map = hashMapOf(
                //                null to 1L,
                4L to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().l(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_float() {
        val map = hashMapOf(
                //                null to 1L,
                5f to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().f(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_double() {
        val map = hashMapOf(
                //                null to 1L,
                6.0 to 1L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().d(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }

    @Test
    fun notEqualTo_date() {
        val map = hashMapOf(
                //                null to 1L,
                Date() to 0L
        )
        Realm.getInstance(context).use { realm ->
            map.forEach {
                val count = realm.where(RequiredTestEntity::class.java)
                        .notEqualTo(RequiredTestEntityNames.recursiveList().date(), it.key)
                        .count()
                Assert.assertThat(count, CoreMatchers.`is`(it.value))
            }
        }
    }
}
