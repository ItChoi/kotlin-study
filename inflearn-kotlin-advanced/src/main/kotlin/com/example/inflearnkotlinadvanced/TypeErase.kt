package com.example.inflearnkotlinadvanced


fun main() {
    val numbers = listOf(1, 2f, 3.0, "ASd", 55L)
    println(numbers)
    println(numbers.filterIsInstance<Int>())
    println(numbers.filterIsInstance<Float>())
    println(numbers.filterIsInstance<Double>())
    println(numbers.filterIsInstance<String>())
    println(numbers.filterIsInstance<Long>())



    //val numbers: List = listOf(1, 2, 3) // 불가능
    val num = 3
    num.toSuperString2() // "Int: 3"

    val str = "ABC"
    str.toSuperString2() // "String: ABC"
    "${str::class.java.name}: ${str}"
}

fun <T> T.toSuperString1() {
    // 타입 소거가 되지 않으면 가능한 코드지만, T가 무엇인지 런타임 때도 알 수 없어 오류 발생
    // T::class -> 클래스 정보를 가져오는 코드
    //println("${T::class.java.name}: ${this}")
}

inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}

inline fun <reified T> T.toSuperString2() {
    println("${T::class.java.name}: ${this}")
}

class TypeErase<T, R, B> {

}

class CageShadow<T : Animal> {
    fun <T : Animal> addAnimal(animal: T) {

    }
}

open class CageV1<T : Animal>{
    fun addAnimal(animal: T) {

    }
}

// CageV2에서 같은 제약 조건을 받아 부모에게 전달하는 방식
class CageV2<T : Animal> : CageV1<T>()

// 타입 파라미터를 명시적으로 지정하는 방식
class GoldFishCageV2 : CageV1<GoldFish> ()