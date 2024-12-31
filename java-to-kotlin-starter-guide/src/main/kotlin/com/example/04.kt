package com.example

/**
 * 4. 코틀린에서 연산자를 다루는 방법
 * -> 단항 연산자 / 산술 연산자
 *
 * -> 비교 연산자와 동등성, 동일성
 * 코틀린은 == 값비교, === 참조 비교, 동등성: 객체 값 동일, 동일: 같은 객체
 * 코틀린의 비교연산자(<, <=, >, >=)는 compareTo 호출
 *
 * -> 논리 연산자 / 코틀린에 있는 특이한 연산자
 * 자바와 동일하게 레이지 연산
 * in -> 포함
 * !in -> 미포함
 * a..b -> a부터 b 까지 범위 객체 생성
 *
 * -> 연산자 오버로딩
 */

fun main() {
    val a = Lec04Main(100)
    val b = Lec04Main(200)

    println(a)
    println(b)
    //if (a < b) { } // getter가 있어야 가능한가?
    //println(a + b) // getter가 있어야 가능한가?
    test1()

}

class Lec04Main constructor(
    amount: Int
) {

}

fun test1() {
    val arr = listOf(1, 2, 3)
    println(1 in arr)
    println(1 !in arr)
}

fun test2() {

}