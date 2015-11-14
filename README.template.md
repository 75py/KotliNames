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
Realm.getInstance(context).use {
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


## Installation (Kotlin)

```groovy
dependencies {
    compile 'io.realm:realm-android:%%realm_version%%'

    compile 'com.nagopy.android:kotlinames:%%version%%'
    kapt 'com.nagopy.android:kotlinames-compiler:%%version%%'
}
```

## Installation (Java)

```groovy
dependencies {
    compile 'io.realm:realm-android:%%realm_version%%'

    apt 'com.nagopy.android:kotlinames-compiler:%%version%%'
}
```
```groovy
apt {
    arguments {
        arg("kotlinames.lang", "java")
    }
}
```


## Additional functions

* RealmQuery&lt;E>.findAllSorted(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>) : RealmResults&lt;E>
* RealmQuery&lt;E>.findAllSortedAsync(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>) : RealmResults&lt;E>
* RealmResults&lt;E>.sort(vararg fieldNameArray: Pair&lt;KSortablePropertyName&lt;*>, Boolean>)
```kotlin
// Example
Realm.getInstance(context).use {
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

        // Programing language("kotlin" or "java")
        // Java mode generates filed name String constants. It's not type safe but typo safe.
        // default: kotlin
        arg("kotlinames.lang", "kotlin")
    }
}

```

## Version

Library version : %%version%%

Supported Realm version: %%realm_version%%
