package com.example.inflearnkotlinadvanced.reflection

import com.example.inflearnkotlinadvanced.Animal
import com.example.inflearnkotlinadvanced.Cage
import com.example.inflearnkotlinadvanced.Carp
import com.example.inflearnkotlinadvanced.GoldFish
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.cast

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))
    cage.getFirst() as Carp // 위험하게 형변환

    // 제네릭을 사용하지 않고, 종류별 Cage를 만드는 것도 가능하다.
    val typeSafeCage = TypeSafeCage()
    typeSafeCage.putOne(GoldFish::class, GoldFish("금붕어"))
    typeSafeCage.putOne(Carp::class, Carp("잉어"))
    //val one: Carp = typeSafeCage.getOne(Carp::class)
    val one1: Carp = typeSafeCage.getOne()
    val one2: Carp = typeSafeCage.getOne<Carp>()

    // SuperTypeToken
    val superTypeToken1 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken2 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken3 = object : SuperTypeToken<List<Carp>>() {}
    println(superTypeToken2.equals(superTypeToken1))
    println(superTypeToken3.equals(superTypeToken1))

    val superTypeSafeCage = SuperTypeSafeCage()
    superTypeSafeCage.putOne(superTypeToken2, listOf(GoldFish("금붕어1"), GoldFish("금붕어2")))
    //val result1 = superTypeSafeCage.getOne(superTypeToken3)
    val result2 = superTypeSafeCage.getOne(superTypeToken2)
    val result3 = superTypeSafeCage.getOne(superTypeToken1)
    //println(result1) // 금붕어 리스트까지 원래 줘야하지만, 안 준다! (슈퍼타입 토큰)
    println(result2) // 금붕어 리스트까지 원래 줘야하지만, 안 준다! (슈퍼타입 토큰)
    println(result3) // 금붕어 리스트까지 원래 줘야하지만, 안 준다! (슈퍼타입 토큰)

}



class TypeSafeCage {
    private val animals: MutableMap<KClass<*>, Animal> = mutableMapOf()

    fun <T : Animal> getOne(type: KClass<T>): T {
        return type.cast(animals[type])
    }

    fun <T : Animal> putOne(type: KClass<T>, animal: T) {
        animals[type] = type.cast(animal)
    }

    inline fun <reified T : Animal> getOne(): T {
        return this.getOne(T::class)
    }

    inline fun <reified T : Animal> putOne(animal: T) {
        this.putOne(T::class, animal)
    }
}

class SuperTypeSafeCage {
    private val animals: MutableMap<SuperTypeToken<*>, Any> = mutableMapOf()

    fun <T : Any> getOne(token: SuperTypeToken<T>): T {
        return this.animals[token] as T
    }

    fun <T : Any> putOne(token: SuperTypeToken<T>, animal: T) {
        animals[token] = animal
    }
}

/**
 * SuperTypeToken을 구현한 클래스가 인스턴스되는 시점에 T 정보를 내부 변수에 저장!
 * List<Int> -> T // 제네릭을 포함한 모든 타입을 기억
 */
abstract class SuperTypeToken<T> {
    val type: KType = this::class.supertypes[0].arguments[0].type!! // SuperTypeToken

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as SuperTypeToken<*>

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}
