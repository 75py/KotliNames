package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.RealmQuery
import java.util.*

fun RealmQuery<out RealmObject>.averageDouble(fieldName: KPropertyName<Double>) = average(fieldName.name())

fun RealmQuery<out RealmObject>.beginsWith(fieldName: KPropertyName<String>, value: String) = beginsWith(fieldName.name(), value)
fun RealmQuery<out RealmObject>.beginsWith(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = beginsWith(fieldName.name(), value, caseSensitive)

fun RealmQuery<out RealmObject>.between(fieldName: KPropertyName<Date>, from: Date, to: Date) = between(fieldName.name(), from, to)
fun RealmQuery<out RealmObject>.between(fieldName: KPropertyName<Double>, from: Double, to: Double) = between(fieldName.name(), from, to)
fun RealmQuery<out RealmObject>.between(fieldName: KPropertyName<Float>, from: Float, to: Float) = between(fieldName.name(), from, to)
fun RealmQuery<out RealmObject>.between(fieldName: KPropertyName<Int>, from: Int, to: Int) = between(fieldName.name(), from, to)
fun RealmQuery<out RealmObject>.between(fieldName: KPropertyName<Long>, from: Long, to: Long) = between(fieldName.name(), from, to)

fun RealmQuery<out RealmObject>.contains(fieldName: KPropertyName<String>, value: String) = contains(fieldName.name(), value)
fun RealmQuery<out RealmObject>.contains(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = contains(fieldName.name(), value, caseSensitive)

fun RealmQuery<out RealmObject>.endsWith(fieldName: KPropertyName<String>, value: String) = endsWith(fieldName.name(), value)
fun RealmQuery<out RealmObject>.endsWith(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = endsWith(fieldName.name(), value, caseSensitive)

fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Boolean>, value: Boolean) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Byte>, value: Byte) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Date>, value: Date) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Double>, value: Double) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Float>, value: Float) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Int>, value: Int) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Long>, value: Long) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<Short>, value: Short) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<String>, value: String) = equalTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.equalTo(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = equalTo(fieldName.name(), value, caseSensitive)


fun RealmQuery<out RealmObject>.findAllSorted(fieldName: KPropertyName<*>) = findAllSorted(fieldName.name())
fun RealmQuery<out RealmObject>.findAllSorted(fieldName: KPropertyName<*>, sortAscending: Boolean) = findAllSorted(fieldName.name(), sortAscending)
fun RealmQuery<out RealmObject>.findAllSorted(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean) = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun RealmQuery<out RealmObject>.findAllSorted(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean, name3: KPropertyName<*>, sortAscending3: Boolean) = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)

fun RealmQuery<out RealmObject>.findAllSortedAsync(fieldName: KPropertyName<*>) = findAllSortedAsync(fieldName.name())
fun RealmQuery<out RealmObject>.findAllSortedAsync(fieldName: KPropertyName<*>, sortAscending: Boolean) = findAllSortedAsync(fieldName.name(), sortAscending)
fun RealmQuery<out RealmObject>.findAllSortedAsync(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean) = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun RealmQuery<out RealmObject>.findAllSortedAsync(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean, name3: KPropertyName<*>, sortAscending3: Boolean) = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)


fun RealmQuery<out RealmObject>.greaterThan(fieldName: KPropertyName<Date>, value: Date) = greaterThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThan(fieldName: KPropertyName<Double>, value: Double) = greaterThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThan(fieldName: KPropertyName<Float>, value: Float) = greaterThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThan(fieldName: KPropertyName<Int>, value: Int) = greaterThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThan(fieldName: KPropertyName<Long>, value: Long) = greaterThan(fieldName.name(), value)

fun RealmQuery<out RealmObject>.greaterThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.greaterThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) = greaterThanOrEqualTo(fieldName.name(), value)

fun RealmQuery<out RealmObject>.isEmpty(fieldName: KPropertyName<*>) = isEmpty(fieldName.name())
fun RealmQuery<out RealmObject>.isNull(fieldName: KPropertyName<*>) = isNull(fieldName.name())
fun RealmQuery<out RealmObject>.isNotNull(fieldName: KPropertyName<*>) = isNotNull(fieldName.name())

fun RealmQuery<out RealmObject>.lessThan(fieldName: KPropertyName<Date>, value: Date) = lessThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThan(fieldName: KPropertyName<Double>, value: Double) = lessThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThan(fieldName: KPropertyName<Float>, value: Float) = lessThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThan(fieldName: KPropertyName<Int>, value: Int) = lessThan(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThan(fieldName: KPropertyName<Long>, value: Long) = lessThan(fieldName.name(), value)

fun RealmQuery<out RealmObject>.lessThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.lessThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) = lessThanOrEqualTo(fieldName.name(), value)

fun RealmQuery<out RealmObject>.maxDate(fieldName: KPropertyName<Date>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxByte(fieldName: KPropertyName<Byte>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxShort(fieldName: KPropertyName<Short>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxInt(fieldName: KPropertyName<Int>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxLong(fieldName: KPropertyName<Long>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxFloat(fieldName: KPropertyName<Float>) = max(fieldName.name())
fun RealmQuery<out RealmObject>.maxDouble(fieldName: KPropertyName<Double>) = max(fieldName.name())

fun RealmQuery<out RealmObject>.minDate(fieldName: KPropertyName<Date>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minByte(fieldName: KPropertyName<Byte>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minShort(fieldName: KPropertyName<Short>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minInt(fieldName: KPropertyName<Int>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minLong(fieldName: KPropertyName<Long>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minFloat(fieldName: KPropertyName<Float>) = min(fieldName.name())
fun RealmQuery<out RealmObject>.minDouble(fieldName: KPropertyName<Double>) = min(fieldName.name())

fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Boolean>, value: Boolean) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Byte>, value: Byte) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Date>, value: Date) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Double>, value: Double) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Float>, value: Float) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Int>, value: Int) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Long>, value: Long) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<Short>, value: Short) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<String>, value: String) = notEqualTo(fieldName.name(), value)
fun RealmQuery<out RealmObject>.notEqualTo(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = notEqualTo(fieldName.name(), value, caseSensitive)

fun RealmQuery<out RealmObject>.sumInt(fieldName: KPropertyName<Int>) = sum(fieldName.name())
fun RealmQuery<out RealmObject>.sumFloat(fieldName: KPropertyName<Float>) = sum(fieldName.name())
fun RealmQuery<out RealmObject>.sumDouble(fieldName: KPropertyName<Double>) = sum(fieldName.name())



