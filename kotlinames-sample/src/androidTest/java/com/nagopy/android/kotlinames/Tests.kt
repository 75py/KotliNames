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

@RunWith(AndroidJUnit4::class)
class Tests {

    lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        Realm.deleteRealm(RealmConfiguration.Builder(context).build())

        Realm.getInstance(context).use {
            it.inTransaction {
                it.where(TestEntity::class.java).findAll().clear()
            }
            it.inTransaction {
                val test1 = it.createObject(TestEntity::class.java)
                test1.string = "defaultString"
                test1.nullString = null
                test1.emptyString = ""
                test1.b = true
                test1.bNullable = null
            }
            it.inTransaction {
                val test2 = it.createObject(TestEntity::class.java)
                test2.string = "defaultString2"
                test2.nullString = null
                test2.emptyString = ""
                test2.b = false
                test2.bNullable = null
            }
        }
    }

    @Test
    fun contains_string() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .contains(TestEntityNames.string, "defaultString") // TypeSafe!
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(2L))
        }
    }

    @Test
    fun equalTo_string() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo(TestEntityNames.string, "defaultString") // TypeSafe!
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_string_number() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo("string", 1)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_string_boolean() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo("string", false)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test
    fun equalTo_boolean() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo(TestEntityNames.b, false) // TypeSafe!
                    .count()
            Assert.assertThat(count, CoreMatchers.`is`(1L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_boolean_number() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo("b", 1)
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun equalTo_boolean_string() {
        Realm.getInstance(context).use {
            val count = it.where(TestEntity::class.java)
                    .equalTo("b", "false")
                    .count()
            // expected Exception
            Assert.assertThat(count, CoreMatchers.`is`(0L))
        }
    }

    fun Realm.inTransaction(func: () -> Unit) {
        beginTransaction()
        try {
            func()
            commitTransaction()
        } catch (t: Throwable) {
            cancelTransaction()
            throw t
        }
    }

}