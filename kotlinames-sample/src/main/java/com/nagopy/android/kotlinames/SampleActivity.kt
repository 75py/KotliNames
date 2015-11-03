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
                    // Type safe
                    .equalTo(CatNames.name, "aaa")

                    // Compile error. Cat.name is String, not Number
                    // .equalTo(CatNames.name, 1.5)

                    // weight is Double value
                    .greaterThan(CatNames.weight, 1.0)
                    // Compile error. Cat.weight cannot compare to String value
                    // .notEqualTo(CatNames.weight, "")
                    .findAll()
        }
    }
}