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

        Realm.getInstance(this).use {
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