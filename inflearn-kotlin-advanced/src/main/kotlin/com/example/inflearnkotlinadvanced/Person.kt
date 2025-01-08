package com.example.inflearnkotlinadvanced

import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Person {
    //var name: String = "홍길동"
    //var name: String? = null
    lateinit var name: String
    val isKim: Boolean
        get() = this.name.startsWith("김")

    // 최태현 -> 최**
    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

fun main() {
    //val p = Person()
    //p.isKim
    val person6 = Person6()
    person6.age = 30
    person6.age = 30
}

class Person2 {
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2000L)
                this._name = "김수한무"
            }
            return this.name!!
        }
}

class Person3 {
    private var _name: String? = null
    val name: String by lazy {
        Thread.sleep(2000L)
        "김수한무"
    }

    val status: String by object : ReadOnlyProperty<Person3, String> {
        private var isGreen: Boolean = false
        override fun getValue(thisRef: Person3, property: KProperty<*>): String {
            return if (isGreen) {
                isGreen = true;
                "Happy"
            } else {
                isGreen = false
                "Sad"
            }
        }
    }
}

class Person4 {
    val delegateProperty = LazyInitProperty {
        Thread.sleep(2000L)
        "김수한무"
    }

    val name: String
        get() = delegateProperty.value
}

class Person5 {
    val name: String by lazy {
        Thread.sleep(2000L)
        "김수한무"
    }
}

class Person6 {
    var age: Int by Delegates.observable(20) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            println("이전 값 : ${oldValue} 새로운 값 : ${newValue}")
        }
    }
}

class LazyInitProperty<T>(
    val init: () -> T
) {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class LazyInitProperty1<T>(
    val init: () -> T
) : ReadOnlyProperty<Any, T> {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class Person7 {
    val name by DelegateProperty("최태현", "name") // name 경우만 정상 동작하게 하는 것 필요
    val country by DelegateProperty("한국", "country")
}

class DelegateProperty(
    private val initValue: String,
    propertyName: String,
) : ReadOnlyProperty<Any, String> {
    init {
        if (propertyName != "name") {
            throw IllegalArgumentException("${propertyName}대신 named 프로퍼티만 사용 가능")
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

class Person8 {
    val name by DelegateProperty1("최태현") // name 경우만 정상 동작하게 하는 것 필요
    val country by DelegateProperty1("한국")
}

class DelegateProvider(
    private val initValue: String
) {
    operator fun provideDelegate(thisRef: Any, property: KProperty<*>): DelegateProperty1 {
        if (property.name != "name") {
            throw IllegalArgumentException("name만 연결 가능")
        }
        return DelegateProperty1(initValue)
    }
}

class DelegateProperty1(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

class DelegateProvider1(
    private val initValue: String
) : PropertyDelegateProvider<Any, DelegateProperty2> {
    override fun provideDelegate(thisRef: Any, property: KProperty<*>): DelegateProperty2 {
        if (property.name != "name") {
            throw IllegalArgumentException("name만 연결 가능")
        }
        return DelegateProperty2(initValue)
    }
}

class DelegateProperty2(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}