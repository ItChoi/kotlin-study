package com.example

/**
 * 18. 코틀린에서 컬렉션을 함수형으로 다루는 방법
 * -> 필터와 맵
 * filter / filterIndexed / map / mapIndexed / mapNotNull
 * fruits.filter { fruit -> fruit.name == "사과" }
 * fruits.filterIndexed { idx, fruit -> fruit.name == "사과" || idx == 0 }
 * fruits.filter { fruit -> fruit.name == "사과" }.map { fruit -> fruit.currentPrice }
 * fruits.filter { fruit -> fruit.name == "사과" }.map { fruit -> fruit.currentPrice }.mapIndexed { idx, fruit -> ... }
 * fruits.filter { fruit -> fruit.name == "사과" }.map { fruit -> fruit.currentPrice }.mapNotNull { fruit -> fruit.nullOrValue() }
 *
 * -> 다양한 컬렉션 처리 기능
 * all -> 람다의 모든 조건을 만족시 true
 * none -> 람다의 모든 조건을 불만족시 true
 * any -> 람다의 조건 중 하나라도 만족시 true
 * count -> list.size
 * sortedBy -> 오름차순
 * sortedByDescending -> 내림차순
 * distinctBy -> 변형된 값 기준 중복 제거
 * first -> 첫 번 째 값을 가져옴, 없는 경우 Exception
 * firstOrNull -> 첫 번 쨰 값 또는 null
 * last -> 마지막 값을 가져옴, 없는 경우 Exception
 * lastOrNull -> 마지막 값 또는 null
 *
 * -> List를 Map으로
 * groupBy -> key 기준 그룹핑 ( name, List<Fruit> )
 * associateBy -> 단일 ( name, Fruit )
 *
 * -> 중첩된 컬렉션 처리
 * flatMap -> List<List<Fruit>> -> List<Fruit>으로 가공
 * flatten -> List<List<Fruit>> -> List<Fruit> 단순 변환
 */

fun main() {
    val fruits = listOf(
        Fruit18(1L, "사과", 1000, 1500),
        Fruit18(2L, "사과", 2000, 1500),
        Fruit18(3L, "바나나", 2000, 1500)
    )
    val test = test01(fruits) { fruit18 -> fruit18.name == "사과" }
    println(test)

    val map1: Map<String, List<Long>> = fruits
        .groupBy({ fruit -> fruit.name }, {fruit -> fruit.factoryPrice })

    val map2: Map<Long, Long> = fruits
        .associateBy({ fruit -> fruit.id }, {fruit -> fruit.factoryPrice })

    val map3: Map<String, List<Fruit18>> = fruits
        .groupBy{ fruit -> fruit.name }
        .filter { (key, value) -> key == "사과" }

    val listList = listOf(
        fruits,
        listOf(
            Fruit18(4L, "사과", 1000, 1500),
            Fruit18(5L, "사과", 2000, 1500),
            Fruit18(6L, "바나나", 2000, 1500)
        )
    )
    val samePriceFruits = listList.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    }
}

data class Fruit18(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {
    val isSamePrice: Boolean
        get() = this.factoryPrice == this.currentPrice
}

private fun test01(
    fruits: List<Fruit18>,
    filter: (Fruit18) -> Boolean
): List<Fruit18> {
    return fruits.filter(filter)
}