package com.nagopy.android.kotlinames

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nagopy.android.kotlinames.names.TestEntityNames
import io.realm.Realm
import io.realm.RealmConfiguration
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SortTest {

    lateinit var context: Context

    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        Realm.deleteRealm(RealmConfiguration.Builder(context).build())

        Realm.getInstance(context).use {
            it.executeTransaction {
                it.where(TestEntity::class.java).findAll().clear()
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
                test1.date = Date()
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
                test2.date = Date()
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
                test3.date = Date()
            }
        }
    }

    @Test
    fun sort() {
        setup()

        Realm.getInstance(context).use { realm ->
            val result = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")
                    .findAll()
            Assert.assertThat(result.size, CoreMatchers.`is`(3))

            result.sort(TestEntityNames.string())
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result.sort(TestEntityNames.string(), false)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result.sort(TestEntityNames.by(), true, TestEntityNames.string(), true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result.sort(TestEntityNames.by(), true, TestEntityNames.i(), true, TestEntityNames.sh(), true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun sort_vararg() {
        setup()

        Realm.getInstance(context).use { realm ->
            val result = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")
                    .findAll()
            Assert.assertThat(result.size, CoreMatchers.`is`(3))

            result.sort(TestEntityNames.string() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result.sort(TestEntityNames.string() to false)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result.sort(TestEntityNames.by() to true, TestEntityNames.string() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result.sort(TestEntityNames.by() to true, TestEntityNames.i() to true, TestEntityNames.sh() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun RealmQuery_findAllSorted() {
        setup()

        Realm.getInstance(context).use { realm ->
            val query = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")

            var result = query.findAllSorted(TestEntityNames.string())
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result = query.findAllSorted(TestEntityNames.string(), false)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result = query.findAllSorted(TestEntityNames.by(), true, TestEntityNames.string(), true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result = query.findAllSorted(TestEntityNames.by(), true, TestEntityNames.i(), true, TestEntityNames.sh(), true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }

    @Test
    fun RealmQuery_findAllSorted_vararg() {
        setup()

        Realm.getInstance(context).use { realm ->
            val query = realm.where(TestEntity::class.java)
                    .contains(TestEntityNames.string(), "string")

            var result = query.findAllSorted(TestEntityNames.string() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            result = query.findAllSorted(TestEntityNames.string() to false)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))

            // by は全て１なので実質stringのみ
            result = query.findAllSorted(TestEntityNames.by() to true, TestEntityNames.string() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string1"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string3"))

            // by, i は全て同じ値
            result = query.findAllSorted(TestEntityNames.by() to true, TestEntityNames.i() to true, TestEntityNames.sh() to true)
            Assert.assertThat(result[0].string, CoreMatchers.`is`("string2"))
            Assert.assertThat(result[1].string, CoreMatchers.`is`("string3"))
            Assert.assertThat(result[2].string, CoreMatchers.`is`("string1"))
        }
    }
}
