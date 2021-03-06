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

import io.realm.*
import java.util.*

fun <E : RealmObject, N : Number> RealmQuery<E>.average(fieldName: KPropertyName<N>): Double = average(fieldName.name())

// nullable
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KNullablePropertyName<String>, value: String?): RealmQuery<E> = beginsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KNullablePropertyName<String>, value: String?, casing: Case) : RealmQuery<E> = beginsWith(fieldName.name(), value, casing)
// required
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KRequiredPropertyName<String>, value: String): RealmQuery<E> = beginsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.beginsWith(fieldName: KRequiredPropertyName<String>, value: String, casing: Case) : RealmQuery<E> = beginsWith(fieldName.name(), value, casing)

fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Date>, from: Date, to: Date): RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Byte>, from: Byte, to: Byte): RealmQuery<E> = between(fieldName.name(), from.toInt(), to.toInt())
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Short>, from: Short, to: Short): RealmQuery<E> = between(fieldName.name(), from.toInt(), to.toInt())
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Int>, from: Int, to: Int): RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Long>, from: Long, to: Long): RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Float>, from: Float, to: Float): RealmQuery<E> = between(fieldName.name(), from, to)
fun <E : RealmObject> RealmQuery<E>.between(fieldName: KPropertyName<Double>, from: Double, to: Double): RealmQuery<E> = between(fieldName.name(), from, to)

// nullable
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KNullablePropertyName<String>, value: String?): RealmQuery<E> = contains(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KNullablePropertyName<String>, value: String?, casing: Case): RealmQuery<E> = contains(fieldName.name(), value, casing)
// required
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KRequiredPropertyName<String>, value: String): RealmQuery<E> = contains(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.contains(fieldName: KRequiredPropertyName<String>, value: String, casing: Case): RealmQuery<E> = contains(fieldName.name(), value, casing)

// nullable
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KNullablePropertyName<String>, value: String?): RealmQuery<E> = endsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KNullablePropertyName<String>, value: String?, casing: Case): RealmQuery<E> = endsWith(fieldName.name(), value, casing)
// required
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KRequiredPropertyName<String>, value: String): RealmQuery<E> = endsWith(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.endsWith(fieldName: KRequiredPropertyName<String>, value: String, casing: Case): RealmQuery<E> = endsWith(fieldName.name(), value, casing)

// nullable
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Boolean>, value: Boolean?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Byte>, value: Byte?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Date>, value: Date?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Double>, value: Double?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Float>, value: Float?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Int>, value: Int?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Long>, value: Long?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<Short>, value: Short?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<String>, value: String?): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KNullablePropertyName<String>, value: String?, casing: Case): RealmQuery<E> = equalTo(fieldName.name(), value, casing)
// required
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Boolean>, value: Boolean): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Byte>, value: Byte): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Date>, value: Date): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Double>, value: Double): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Float>, value: Float): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Int>, value: Int): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Long>, value: Long): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<Short>, value: Short): RealmQuery<E> = equalTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<String>, value: String): RealmQuery<E> = equalTo(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.equalTo(fieldName: KRequiredPropertyName<String>, value: String, casing: Case): RealmQuery<E> = equalTo(fieldName.name(), value, casing)


fun <E : RealmObject> RealmQuery<E>.findAllSorted(fieldName: KSortablePropertyName<*>): RealmResults<E> = findAllSorted(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.findAllSorted(fieldName: KSortablePropertyName<*>, sortOrder: Sort): RealmResults<E> = findAllSorted(fieldName.name(), sortOrder)
fun <E : RealmObject> RealmQuery<E>.findAllSorted(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort): RealmResults<E> = findAllSorted(name1.name(), sortOrder1, name2.name(), sortOrder2)
fun <E : RealmObject> RealmQuery<E>.findAllSorted(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort, name3: KSortablePropertyName<*>, sortOrder3: Sort): RealmResults<E> = findAllSorted(arrayOf(name1.name(), name2.name(), name3.name()), arrayOf(sortOrder1, sortOrder2, sortOrder3))
fun <E : RealmObject> RealmQuery<E>.findAllSorted(vararg fieldNameArray: Pair<KSortablePropertyName<*>, Sort>): RealmResults<E> {
    val fieldNames = fieldNameArray.map { it.first.name() }.toTypedArray()
    val sortOrder = fieldNameArray.map { it.second }.toTypedArray()
    return findAllSorted(fieldNames, sortOrder)
}

fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(fieldName: KSortablePropertyName<*>): RealmResults<E> = findAllSortedAsync(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(fieldName: KSortablePropertyName<*>, sortOrder: Sort): RealmResults<E> = findAllSortedAsync(fieldName.name(), sortOrder)
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort): RealmResults<E> = findAllSortedAsync(name1.name(), sortOrder1, name2.name(), sortOrder2)
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort, name3: KSortablePropertyName<*>, sortOrder3: Sort): RealmResults<E> = findAllSortedAsync(arrayOf(name1.name(), name2.name(), name3.name()), arrayOf(sortOrder1, sortOrder2, sortOrder3))
fun <E : RealmObject> RealmQuery<E>.findAllSortedAsync(vararg fieldNameArray: Pair<KSortablePropertyName<*>, Sort>): RealmResults<E> {
    val fieldNames = fieldNameArray.map { it.first.name() }.toTypedArray()
    val sortOrder = fieldNameArray.map { it.second }.toTypedArray()
    return findAllSortedAsync(fieldNames, sortOrder)
}


fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Date>, value: Date): RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Byte>, value: Byte): RealmQuery<E> = greaterThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Short>, value: Short): RealmQuery<E> = greaterThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Int>, value: Int): RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Long>, value: Long): RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Float>, value: Float): RealmQuery<E> = greaterThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThan(fieldName: KPropertyName<Double>, value: Double): RealmQuery<E> = greaterThan(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Byte>, value: Byte): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Short>, value: Short): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.greaterThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double): RealmQuery<E> = greaterThanOrEqualTo(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.isEmpty(fieldName: KPropertyName<*>): RealmQuery<E> = isEmpty(fieldName.name())
// nullable only
fun <E : RealmObject> RealmQuery<E>.isNull(fieldName: KNullablePropertyName<*>): RealmQuery<E> = isNull(fieldName.name())
fun <E : RealmObject> RealmQuery<E>.isNotNull(fieldName: KNullablePropertyName<*>): RealmQuery<E> = isNotNull(fieldName.name())

fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Date>, value: Date): RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Byte>, value: Byte): RealmQuery<E> = lessThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Short>, value: Short): RealmQuery<E> = lessThan(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Int>, value: Int): RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Long>, value: Long): RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Float>, value: Float): RealmQuery<E> = lessThan(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThan(fieldName: KPropertyName<Double>, value: Double): RealmQuery<E> = lessThan(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Date>, value: Date): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Byte>, value: Byte): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Short>, value: Short): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value.toInt())
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Int>, value: Int): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Long>, value: Long): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Float>, value: Float): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.lessThanOrEqualTo(fieldName: KPropertyName<Double>, value: Double): RealmQuery<E> = lessThanOrEqualTo(fieldName.name(), value)

fun <E : RealmObject> RealmQuery<E>.maximumDate(fieldName: KPropertyName<Date>): Date = maximumDate(fieldName.name())
fun <E : RealmObject, N : Number> RealmQuery<E>.max(fieldName: KPropertyName<N>): Number = max(fieldName.name())

fun <E : RealmObject> RealmQuery<E>.minimumDate(fieldName: KPropertyName<Date>): Date = minimumDate(fieldName.name())
fun <E : RealmObject, N : Number> RealmQuery<E>.min(fieldName: KPropertyName<N>): Number = min(fieldName.name())

// nullable
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Boolean>, value: Boolean?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Byte>, value: Byte?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Date>, value: Date?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Double>, value: Double?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Float>, value: Float?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Int>, value: Int?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Long>, value: Long?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<Short>, value: Short?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<String>, value: String?): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KNullablePropertyName<String>, value: String?, casing: Case): RealmQuery<E> = notEqualTo(fieldName.name(), value, casing)
// required
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Boolean>, value: Boolean): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Byte>, value: Byte): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Date>, value: Date): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Double>, value: Double): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Float>, value: Float): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Int>, value: Int): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Long>, value: Long): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<Short>, value: Short): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<String>, value: String): RealmQuery<E> = notEqualTo(fieldName.name(), value)
fun <E : RealmObject> RealmQuery<E>.notEqualTo(fieldName: KRequiredPropertyName<String>, value: String, casing: Case): RealmQuery<E> = notEqualTo(fieldName.name(), value, casing)

fun <E : RealmObject, N : Number> RealmQuery<E>.sum(fieldName: KPropertyName<N>): Number = sum(fieldName.name())

// RealmResults
fun <E : RealmObject> RealmResults<E>.sort(fieldName: KSortablePropertyName<*>) = sort(fieldName.name())

fun <E : RealmObject> RealmResults<E>.sort(fieldName: KSortablePropertyName<*>, sortOrder: io.realm.Sort) = sort(fieldName.name(), sortOrder)
fun <E : RealmObject> RealmResults<E>.sort(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort) = sort(name1.name(), sortOrder1, name2.name(), sortOrder2)
fun <E : RealmObject> RealmResults<E>.sort(name1: KSortablePropertyName<*>, sortOrder1: Sort, name2: KSortablePropertyName<*>, sortOrder2: Sort, name3: KSortablePropertyName<*>, sortOrder3: Sort) = sort(arrayOf(name1.name(), name2.name(), name3.name()), arrayOf(sortOrder1, sortOrder2, sortOrder3))
fun <E : RealmObject> RealmResults<E>.sort(vararg fieldNameArray: Pair<KSortablePropertyName<*>, Sort>) : RealmResults<E> {
    val fieldNames = fieldNameArray.map { it.first.name() }.toTypedArray()
    val sortOrder = fieldNameArray.map { it.second }.toTypedArray()
    return sort(fieldNames, sortOrder)
}
