package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.RealmQuery
import java.util.*

fun RealmQuery<*>.averageDouble(fieldName: KPropertyName<Double>) = average(fieldName.name())

fun RealmQuery<*>.beginsWith(fieldName: KPropertyName<String>, value: String) = beginsWith(fieldName.name(), value)
fun RealmQuery<*>.beginsWith(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = beginsWith(fieldName.name(), value, caseSensitive)

fun RealmQuery<*>.between(fieldName: KPropertyName<Date>, from: Date, to: Date) = between(fieldName.name(), from, to)
fun RealmQuery<*>.between(fieldName: KPropertyName<Double>, from: Double, to: Double) = between(fieldName.name(), from, to)
fun RealmQuery<*>.between(fieldName: KPropertyName<Float>, from: Float, to: Float) = between(fieldName.name(), from, to)
fun RealmQuery<*>.between(fieldName: KPropertyName<Int>, from: Int, to: Int) = between(fieldName.name(), from, to)
fun RealmQuery<*>.between(fieldName: KPropertyName<Long>, from: Long, to: Long) = between(fieldName.name(), from, to)

fun RealmQuery<*>.contains(fieldName: KPropertyName<String>, value: String) = contains(fieldName.name(), value)
fun RealmQuery<*>.contains(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = contains(fieldName.name(), value, caseSensitive)

fun RealmQuery<*>.endsWith(fieldName: KPropertyName<String>, value: String) = endsWith(fieldName.name(), value)
fun RealmQuery<*>.endsWith(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = endsWith(fieldName.name(), value, caseSensitive)

fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Boolean>, value: Boolean) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Byte>, value: Byte) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Date>, value: Date) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Double>, value: Double) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Float>, value: Float) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Int>, value: Int) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Long>, value: Long) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<Short>, value: Short) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<String>, value: String) = equalTo(fieldName.name(), value)
fun RealmQuery<*>.equalTo(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = equalTo(fieldName.name(), value, caseSensitive)


fun RealmQuery<*>.findAllSorted(fieldName: KPropertyName<*>) = findAllSorted(fieldName.name())
fun RealmQuery<*>.findAllSorted(fieldName: KPropertyName<*>, sortAscending: Boolean) = findAllSorted(fieldName.name(), sortAscending)
fun RealmQuery<*>.findAllSorted(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean) = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun RealmQuery<*>.findAllSorted(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean, name3: KPropertyName<*>, sortAscending3: Boolean) = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)

fun RealmQuery<*>.findAllSortedAsync(fieldName: KPropertyName<*>) = findAllSortedAsync(fieldName.name())
fun RealmQuery<*>.findAllSortedAsync(fieldName: KPropertyName<*>, sortAscending: Boolean) = findAllSortedAsync(fieldName.name(), sortAscending)
fun RealmQuery<*>.findAllSortedAsync(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean) = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun RealmQuery<*>.findAllSortedAsync(name1: KPropertyName<*>, sortAscending1: Boolean, name2: KPropertyName<*>, sortAscending2: Boolean, name3: KPropertyName<*>, sortAscending3: Boolean) = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)


fun RealmQuery<*>.greaterThan(fieldName: KPropertyName<Date>, value: Date) = greaterThan(fieldName.name(), value)
fun RealmQuery<*>.greaterThan(fieldName: KPropertyName<Double>, value: Double) = greaterThan(fieldName.name(), value)
fun RealmQuery<*>.greaterThan(fieldName: KPropertyName<Float>, value: Float) = greaterThan(fieldName.name(), value)
fun RealmQuery<*>.greaterThan(fieldName: KPropertyName<Int>, value: Int) = greaterThan(fieldName.name(), value)
fun RealmQuery<*>.greaterThan(fieldName: KPropertyName<Long>, value: Long) = greaterThan(fieldName.name(), value)

fun RealmQuery<*>.greaterThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.greaterThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.greaterThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.greaterThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) = greaterThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.greaterThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) = greaterThanOrEqualTo(fieldName.name(), value)

fun RealmQuery<*>.isEmpty(fieldName: KPropertyName<*>) = isEmpty(fieldName.name())
fun RealmQuery<*>.isNull(fieldName: KPropertyName<*>) = isNull(fieldName.name())
fun RealmQuery<*>.isNotNull(fieldName: KPropertyName<*>) = isNotNull(fieldName.name())

fun RealmQuery<*>.lessThan(fieldName: KPropertyName<Date>, value: Date) = lessThan(fieldName.name(), value)
fun RealmQuery<*>.lessThan(fieldName: KPropertyName<Double>, value: Double) = lessThan(fieldName.name(), value)
fun RealmQuery<*>.lessThan(fieldName: KPropertyName<Float>, value: Float) = lessThan(fieldName.name(), value)
fun RealmQuery<*>.lessThan(fieldName: KPropertyName<Int>, value: Int) = lessThan(fieldName.name(), value)
fun RealmQuery<*>.lessThan(fieldName: KPropertyName<Long>, value: Long) = lessThan(fieldName.name(), value)

fun RealmQuery<*>.lessThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.lessThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.lessThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.lessThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) = lessThanOrEqualTo(fieldName.name(), value)
fun RealmQuery<*>.lessThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) = lessThanOrEqualTo(fieldName.name(), value)

fun RealmQuery<*>.maxDate(fieldName: KPropertyName<Date>) = max(fieldName.name())
fun RealmQuery<*>.maxByte(fieldName: KPropertyName<Byte>) = max(fieldName.name())
fun RealmQuery<*>.maxShort(fieldName: KPropertyName<Short>) = max(fieldName.name())
fun RealmQuery<*>.maxInt(fieldName: KPropertyName<Int>) = max(fieldName.name())
fun RealmQuery<*>.maxLong(fieldName: KPropertyName<Long>) = max(fieldName.name())
fun RealmQuery<*>.maxFloat(fieldName: KPropertyName<Float>) = max(fieldName.name())
fun RealmQuery<*>.maxDouble(fieldName: KPropertyName<Double>) = max(fieldName.name())

fun RealmQuery<*>.minDate(fieldName: KPropertyName<Date>) = min(fieldName.name())
fun RealmQuery<*>.minByte(fieldName: KPropertyName<Byte>) = min(fieldName.name())
fun RealmQuery<*>.minShort(fieldName: KPropertyName<Short>) = min(fieldName.name())
fun RealmQuery<*>.minInt(fieldName: KPropertyName<Int>) = min(fieldName.name())
fun RealmQuery<*>.minLong(fieldName: KPropertyName<Long>) = min(fieldName.name())
fun RealmQuery<*>.minFloat(fieldName: KPropertyName<Float>) = min(fieldName.name())
fun RealmQuery<*>.minDouble(fieldName: KPropertyName<Double>) = min(fieldName.name())

fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Boolean>, value: Boolean) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Byte>, value: Byte) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Date>, value: Date) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Double>, value: Double) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Float>, value: Float) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Int>, value: Int) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Long>, value: Long) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<Short>, value: Short) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<String>, value: String) = notEqualTo(fieldName.name(), value)
fun RealmQuery<*>.notEqualTo(fieldName: KPropertyName<String>, value: String, caseSensitive: Boolean) = notEqualTo(fieldName.name(), value, caseSensitive)

fun RealmQuery<*>.sumInt(fieldName: KPropertyName<Int>) = sum(fieldName.name())
fun RealmQuery<*>.sumFloat(fieldName: KPropertyName<Float>) = sum(fieldName.name())
fun RealmQuery<*>.sumDouble(fieldName: KPropertyName<Double>) = sum(fieldName.name())



