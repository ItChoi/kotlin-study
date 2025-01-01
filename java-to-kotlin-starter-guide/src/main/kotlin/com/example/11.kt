package com.example

/**
 * 11강. 코틀린에서 접근 제어를 다루는 방법
 * -> 자바와 코틀린의 가시성 제어
 * - 코틀린 접근 제어자
 * public -> 동일
 * private -> 동일
 * protected -> 선언된 클래스 또는 하위 클래스에서 접근 가능 (패키지가 제외 - 네임스페이스 관리 용도로 패키지를 영역 구분 용도로 사용), 파일에 사용 불가
 * internal -> 같은 모듈에서만 접근 가능
 * 코트린 default -> public, 자바는 default
 * kt 확장자 파일에 변수, 함수, 클래스 등 여러 개 만들기 가능
 *
 * -> 코틀린 파일의 접근 제어
 * -> 다양한 구성요소의 접근 제어
 * -> Java와 Kotlin을 함께 사용할 경우 주의할 점
 */


private val NUM1 =  3
//protected val NUM2 =  3

fun main() {

}

open class Cat1 protected constructor(
    internal val name: String,
    _price: Int
) {

    // setter만 private 변경
    var price = _price
        private set
}

class Car(
    internal val name: String,
    private var owner: String,
    _price: Int
) {
    var price = _price
        private set
}