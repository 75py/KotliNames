package com.nagopy.android.kotlinames

import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.RealmResults
import java.util.*

fun <E : RealmObject, N : Number> RealmQuery<E>.average(fieldName: KPropertyName<N>) : Double = average(fieldName.name())

// nullable
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KPropertyName<String>, value: String?) : RealmQuery<E> = beginsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KPropertyName<String>, value: String?, caseSensitive: Boolean) : RealmQuery<E> = beginsWith(fieldName.name(), value, caseSensitive)

fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Date>, from: Date, to: Date) : RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Byte>, from: Byte, to: Byte) : RealmQuery<E> = between(fieldName.name(), from.toInt(), to.toInt())
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Short>, from: Short, to: Short) : RealmQuery<E> = between(fieldName.name(), from.toInt(), to.toInt())
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Int>, from: Int, to: Int) : RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Long>, from: Long, to: Long) : RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Float>, from: Float, to: Float) : RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Double>, from: Double, to: Double) : RealmQuery<E> = between(fieldName.name(), from, to)

// nullable
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KPropertyName<String>, value: String?) : RealmQuery<E> = contains(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KPropertyName<String>, value: String?, caseSensitive: Boolean) : RealmQuery<E> = contains(fieldName.name(), value, caseSensitive)

// nullable
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KPropertyName<String>, value: String?) : RealmQuery<E> = endsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KPropertyName<String>, value: String?, caseSensitive: Boolean) : RealmQuery<E> = endsWith(fieldName.name(), value, caseSensitive)

// nullable
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Boolean>, value: Boolean?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Byte>, value: Byte?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Date>, value: Date?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Double>, value: Double?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Float>, value: Float?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Int>, value: Int?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Long>, value: Long?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<Short>, value: Short?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<String>, value: String?) : RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KPropertyName<String>, value: String?, caseSensitive: Boolean) : RealmQuery<E> = equalTo(fieldName.name(), value, caseSensitive)


fun <E : RealmObject> RealmQuery<E>.findAllSorted(fieldName: KSortablePropertyName<*>) : RealmResults<E> = findAllSorted(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.findAllSorted(fieldName: KSortablePropertyName<*>, sortAscending: Boolean) : RealmResults<E> = findAllSorted(fieldName.name(), sortAscending)
fun <E : RealmObject> RealmQuery<E>.findAllSorted(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean) : RealmResults<E> = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun <E : RealmObject> RealmQuery<E>.findAllSorted(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean, name3: KSortablePropertyName<*>, sortAscending3: Boolean) : RealmResults<E> = findAllSorted(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)

fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(fieldName: KSortablePropertyName<*>) : RealmResults<E> = findAllSortedAsync(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(fieldName: KSortablePropertyName<*>, sortAscending: Boolean) : RealmResults<E> = findAllSortedAsync(fieldName.name(), sortAscending)
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean) : RealmResults<E> = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean, name3: KSortablePropertyName<*>, sortAscending3: Boolean) : RealmResults<E> = findAllSortedAsync(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)


fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Date>, value: Date) : RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Byte>, value: Byte) : RealmQuery<E> = greaterThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Short>, value: Short) : RealmQuery<E> = greaterThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Int>, value: Int) : RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Long>, value: Long) : RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Float>, value: Float) : RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Double>, value: Double) : RealmQuery<E> = greaterThan(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Byte>, value: Byte) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Short>, value: Short) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) : RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.isEmpty(fieldName: KPropertyName<*>) : RealmQuery<E> = isEmpty(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.isNull(fieldName: KPropertyName<*>) : RealmQuery<E> = isNull(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.isNotNull(fieldName: KPropertyName<*>) : RealmQuery<E> = isNotNull(fieldName.name())

fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Date>, value: Date) : RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Byte>, value: Byte) : RealmQuery<E> = lessThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Short>, value: Short) : RealmQuery<E> = lessThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Int>, value: Int) : RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Long>, value: Long) : RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Float>, value: Float) : RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Double>, value: Double) : RealmQuery<E> = lessThan(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Byte>, value: Byte) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Short>, value: Short) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double) : RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.maximumDate(fieldName: KPropertyName<Date>) : Date = maximumDate(fieldName.name())
fun <E : RealmObject, N : Number> RealmQuery<E>.max(fieldName: KPropertyName<N>) : Number = max(fieldName.name())

fun <E : RealmObject> RealmQuery<E>.minimumDate(fieldName: KPropertyName<Date>) : Date = minimumDate(fieldName.name())
fun <E : RealmObject, N : Number> RealmQuery<E>.min(fieldName: KPropertyName<N>) : Number = min(fieldName.name())

// nullable
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Boolean>, value: Boolean?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Byte>, value: Byte?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Date>, value: Date?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Double>, value: Double?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Float>, value: Float?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Int>, value: Int?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Long>, value: Long?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<Short>, value: Short?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<String>, value: String?) : RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KPropertyName<String>, value: String?, caseSensitive: Boolean) : RealmQuery<E> = notEqualTo(fieldName.name(), value, caseSensitive)

fun <E : RealmObject, N : Number> RealmQuery<E>.sum(fieldName: KPropertyName<N>) : Number = sum(fieldName.name())

// RealmResults
fun <E : RealmObject> RealmResults<E>.sort(fieldName: KSortablePropertyName<*>) = sort(fieldName.name())
fun <E : RealmObject> RealmResults<E>.sort(fieldName: KSortablePropertyName<*>, sortAscending: Boolean) = sort(fieldName.name(), sortAscending)
fun <E : RealmObject> RealmResults<E>.sort(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean) = sort(name1.name(), sortAscending1, name2.name(), sortAscending2)
fun <E : RealmObject> RealmResults<E>.sort(name1: KSortablePropertyName<*>, sortAscending1: Boolean, name2: KSortablePropertyName<*>, sortAscending2: Boolean, name3: KSortablePropertyName<*>, sortAscending3: Boolean) = sort(name1.name(), sortAscending1, name2.name(), sortAscending2, name3.name(), sortAscending3)
