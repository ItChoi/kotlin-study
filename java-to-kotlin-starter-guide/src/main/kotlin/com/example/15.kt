package com.example

/**
 * 15. 코틀린에서 배열과 컬렉션을 다루는 방법
 * -> 배열
 * 잘 사용하진 않는다. 이펙티브 자바에서도 배열보단 리스트 사용 권장
 * 간단히 문법 사용 정도면 익히면 된다.
 *
 * -> 코틀린에서의 Collection - List, Set, Ma
 * val, val 처럼 불변, 가변을 설정해야 한다.
 * 컬렉션 중 Iterable이 가장 상위에 위치한다.
 * 가변(Mutable) 컬렉션: 컬렉션에 element 추가, 삭제 가능
 * 불변 컬렉션: 컬렉션에 element 추가, 삭제 불가능 -> 불변이더라도 컬렉션 안 ref 필드는 변경 가능
 * 배열처럼 list[0] 가능 list.get(0)도 가능
 *
 * -> 컬렉션의 null 가능성, Java와 함께 사용하기
 * List<Int?> -> null 추가 가능, 자료구조 Null 불가
 * List<Int>? -> null 추가 불가, 자료구조 Null 가능
 * List<Int?>? -> 다 null 가능
 */

fun main() {
    val array = arrayOf(100, 200)
    // 배열 추가 -> 엘리먼트 추가는 되지만 이 코드 이후 로직에서는 사라진다(?)
    array.plus(300)

    for (i in array.indices) {
        println("${i} - ${array[i]}")
    }

    for ((idx, value) in array.withIndex()) {
        println("${idx} - ${value}")
    }

    //val numbers = listOf(100, 200) // 불변
    val numbers = mutableListOf(100, 200) // 가변
    // 리스트 추가 -> 엘리먼트 추가는 되지만 이 코드 이후 로직에서는 사라진다(?)
    numbers.add(300)
    val emptyList = emptyList<Int>() // 비어있는 경우 타입 추론을 하지 못해 타입 지정 필요
    test01(emptyList())

    for (i in array.indices) {
        println("${i} - ${array[i]}")
    }

    mutableSetOf(300L)

    val oldMap = mutableMapOf<Int, String>()
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"

    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((key, value) in oldMap.entries) {
        println(key)
        println(value)
    }

}

private fun test01(numbers: List<Int>) {

}