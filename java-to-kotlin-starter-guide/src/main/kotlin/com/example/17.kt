package com.example

/**
 * 17. 코틀린에서 람다를 다루는 방법
 * -> Java에서 람다를 다루기 위한 노력
 *
 * -> 코틀린에서의 람다
 * 코틀린은 함수가 그 자체로 값이 될 수 있다 -> 함수는 파라미터, 변수 할당이 다 된다.
 *
 * -> Closure
 * 자바 람다는 밖에 변수 사용시 제약이 있다. 실질적으로 final인 변수만 사용 가능, final이 안 붙어도, 변경된 코드가 존재하면 사용 불가
 * 코틀린은 문제없이 사용 가능하다. -> 람다 시작시 참조 변수를 모두 포획해 정보를 갖고 있다. 이러한 데이터 구조를 클로저라고 부른다.
 *
 * -> try with resources
 * use 함수 사용
 * public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R { }
 * - Closeable의 확장 함수 -> 타입.use / T에 대한 확장 함수
 * -
 *
 */

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("바나나", 2500),
        Fruit("수박", 10000),
    )


    println(isApple(fruits[0]))
    println(isApple.invoke(fruits[0])) // 명시 작성을 통해 함수 호출 가능
    println(isApple.invoke(fruits[0])) // 명시 작성을 통해 함수 호출 가능

    val test01 = test01(fruits, isApple)
    for (fruit in test01) {
        println("test01: " + fruit.getName)
    }
    val test02 = test01(fruits, { fruit: Fruit -> fruit.getName == "사과" }) // 바로 익명함수를 파라미터로 넣어줘도 된다
    val test03 = test01(fruits, { fruit -> fruit.getName == "사과" })
    val test04 = test01(fruits) { fruit -> fruit.getName == "사과" } // 추천 방식
    val test05 = test01(fruits) { it.getName == "사과" } // it -> fruit, it 데이터가 어떤 데이터인지 타고 들어가야 해서 비추
}

// 함수명이 없다. -> 람다
val isApple = fun(fruit: Fruit): Boolean {
    println("fun isApple")
    return fruit.getName == "사과"
}


val isApple01: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean {
    return fruit.getName == "사과"
}

val isApple2 = { fruit: Fruit -> fruit.getName == "사과" }
val isApple22: (Fruit) -> Boolean = { fruit: Fruit -> fruit.getName == "사과" }

private fun test01(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean // Fruit 타입을 파라미터로 받아, Boolean 반환하는 함수를 파라미터로 받는다.
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }

    return results
}

class Fruit (
    private val name: String,
    private var price: Int
) {
    val getName: String
        get() = this.name

    val getPrice: Int
        get() = this.price
}