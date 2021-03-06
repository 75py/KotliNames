# KotliNames

Realm type safe API for Kotlin!

## Example

```kotlin
// Your RealmObject. You don't need to change anything.
@RealmClass
open class Cat : RealmObject(){
    @Required
    open var name: String = ""

    open var weight : Double? = null

    open var children: RealmList<Cat>? = null
}
```

```java
// Generated class
public class CatNames {
  public static KRequiredStringPropertyName name() {
    return new KRequiredStringPropertyName("name");
  }

  public static KNullableDoublePropertyName weight() {
    return new KNullableDoublePropertyName("weight");
  }

  public static CatRelationshipNames children() {
    return new CatRelationshipNames("children");
  }
}

public class CatRelationshipNames {
  private final String prefix;

  public CatRelationshipNames(String prefix) {
    this.prefix = prefix + ".";
  }

  public KRequiredStringPropertyName name() {
    return new KRequiredStringPropertyName(prefix + "name");
  }

  public KNullableDoublePropertyName weight() {
    return new KNullableDoublePropertyName(prefix + "weight");
  }

  public CatRelationshipNames children() {
    return new CatRelationshipNames(prefix + "children");
  }
}
```

```kotlin
// Usage
Realm.getDefaultInstance().use {
    it.where(Cat::class.java)
        // Type safe! Cat.name can compare to String type only.
        .equalTo(CatNames.name, "john")

        // This is a compile error because Cat.name is String type.
        .equalTo(CatNames.name, 1.5)

        // This is a compile error because Cat.name is @Required field.
        .equalTo(CatNames.name, null)

        // Cat.weight is Double value
        .greaterThan(CatNames.weight, 5.0)

        // This is a compile error. Cat.weight cannot compare to String value
        .notEqualTo(CatNames.weight, "")

        // Relationships is also supported!
        .equalTo(CatNames.children().name(), "tom")

        .findAll()
}
```


## Installation

```groovy
dependencies {
    compile 'io.realm:realm-android:2.2.2'

    // for Kotlin (Recommended)
    compile 'com.nagopy.android:kotlinames:6.0.0'
    kapt 'com.nagopy.android:kotlinames-compiler:6.0.0'

    // for Java (not type safe but typo safe)
    compile 'com.nagopy.android:kotlinamesj:6.0.0'
    apt 'com.nagopy.android:kotlinames-compiler:6.0.0'
}
```


## Additional functions

* RealmQuery&lt;E>.findAllSorted(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>) : RealmResults&lt;E>
* RealmQuery&lt;E>.findAllSortedAsync(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>) : RealmResults&lt;E>
* RealmResults&lt;E>.sort(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>)
```kotlin
// Example
Realm.getDefaultInstance().use {
    it.where(Cat::class.java)
        .findAllSorted(
            CatNames.age to RealmResults.SORT_ORDER_ASCENDING
            , CatNames.weight to RealmResults.SORT_ORDER_ASCENDING
            , CatNames.name to RealmResults.SORT_ORDER_DESCENDING
            , CatNames.birthday to RealmResults.SORT_ORDER_DESCENDING
        )
}
```


## Option
```groovy
kapt {
    generateStubs = true
    arguments {
        // [For Development, Optional] If true, outputs debug logs.
        // default: false
        arg("kotlinames.debug", "true")
    }
}

```

## Version

Latest KotliNames version : 6.0.0
Supported Realm version: 2.2.2

|Kotlin|Realm|KotliNames|
|---|---|---|
|1.0.6|2.2.2|6.0.0|
|-|1.x|Not supported|
|1.0.0|0.86.0-|5.0.0-|
|1.0.0-rc-1036|0.86.0-|4.0.0-|
|-1.0.0-beta-xxxx|0.86.0-|3.0.0-|
|-1.0.0-beta-xxxx|-0.85.1|-2.1.1|


## License

```
Copyright 2015 75py

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
