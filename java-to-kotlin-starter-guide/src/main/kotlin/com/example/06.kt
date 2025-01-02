package com.example

/**
 * 6. 코틀린에서 반복문을 다루는 방법
 * -> for-each문
 * 자바에서 콜론 대신 코틀린에서는 in을 사용 ( : -> in )
 *
 * -> for문
 *
 * -> progression과 Range
 * 1..3 연산자 -> 범위 연산자 (progression과 Range를 만들 수 있는 것) -> 등차수열을 만드는 로직(시작 값, 끝 값, 공차)
 * step (함수)
 * downTo (함수)
 *
 * -> while 문
 * 자바와 동일
 */

fun main() {
    val numbers = listOf(1L, 2L, 3L)
    for (number in numbers) {
        println(number)
    }

}

private fun test01() {
    for (i in 1..3) {
        println(i)
    }

    for (i in 1..5 step 2) {
        println(i)
    }
    for (i in 3 downTo  1) {
        println(i)
    }
}
