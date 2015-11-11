Change Log
==========

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
