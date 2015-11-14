Change Log
==========

Version 2.0.1 *(2015-11-14)*
----------------------------
* Support Realm 0.84.2
* Support Required annotation
NULL可・不可でメソッドを分離、NULL不可の場合はvalueにnullを受け付けないよう拡張関数を変更
* Support relationships
* Sort method can use sortable types only
ソート不可のフィールド型（byte[]、リレーションシップ）はソート関数に指定できないよう拡張関数を変更
* Add convenient vararg method
ソートするフィールド・昇順降順のペアを可変長で受け取る拡張関数を追加

Version 1.1.0 *(2015-11-11)*
----------------------------

* Add Extension Functions to RealmResults.sort
```java
val result = realm.where(TestEntity::class.java)
        .contains(CatNames.name, "tama")
        .findAll()
result.sort(CatNames.weight)
```
* Add option "kotlinames.debug" to remove debug logs
```groovy
kapt {
    arguments {
        arg("kotlinames.debug", "true")
    }
}
```
