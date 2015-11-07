# KotliNames

RealmQuery typesafe API for Kotlin!

## Example

```java
// Your RealmObject. You don't need to change anything.
@RealmClass
open class Cat : RealmObject(){
    open var name: String? = null
    open var weight : Double? = null
}
```

```java
// Generated class
public class CatNames {
  public static final KPropertyName<java.lang.String> name = new KPropertyName<java.lang.String>() {
    @java.lang.Override
    public java.lang.String name() {
      return toString();
    }
    @java.lang.Override
    public java.lang.String toString() {
      return "name";
    }
  }
  ;

  public static final KPropertyName<java.lang.Double> weight = new KPropertyName<java.lang.Double>() {
    @java.lang.Override
    public java.lang.String name() {
      return toString();
    }
    @java.lang.Override
    public java.lang.String toString() {
      return "weight";
    }
  }
  ;
}
```

```java
// Usage
Realm.getInstance(context).use {
    it.where(Cat::class.java)
        // Type safe! Cat.name can compare to String type only.
        .equalTo(CatNames.name, "john")

        // This is a compile error because Cat.name is String type.
        .equalTo(CatNames.name, 1.5)

        // Cat.weight is Double value
        .greaterThan(CatNames.weight, 5.0)

        // This is a compile error. Cat.weight cannot compare to String value
        .notEqualTo(CatNames.weight, "")

        .findAll()
}
```


## Installation

```groovy
dependencies {
    compile 'io.realm:realm-android:0.84.1'

    compile 'com.nagopy.android:kotlinames:1.0.0'
    kapt 'com.nagopy.android:kotlinames-compiler:1.0.0'
}
```

## Version

Library version : 1.0.0

Supported Realm version: 0.84.1
