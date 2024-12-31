package com.example.javatokotlinstarterguide

/**
 * 1. 변수를 다루는 방법
 * val -> 불변
 * var -> 변수
 * 일반적으로 불변으로 관리하다가, 동적으로 var 사용
 *
 * 코틀린의 불변은 값을 초기화 하지 않아도 된다. 처음 값을 할당하는 순간 불변이 된다.
 * var num = 10L
 * val num = 10L
 * 타입을 컴파일러가 자동으로 추론해준다.
 * 값을 넣지 않는다면 추론할 수 없기에 타입을 명시해주야 한다.
 * -> var num: Long
 *
 * val 컬렉션 자료 구조 -> 엘리먼트들은 추가 가능하다.
 *
 * 자바에서 ref Type, primitive type은 암묵적으로 변환이 가능하며 박싱 언박싱이 된다. -> 연산시 primitive type 사용
 * 코틀린에서는 문제 없다. 내부적으로 특별한 표현을 갖는다. 즉 상황에 따라 원시 타입, 레퍼 타입 사용한다.
 * -> var number1:Long = 10L 연산시 코틀린이 내부적으로 원시 타입으로 연산을 하게 해준다.
 *
 * nullable 변수
 * -> 자바와 다르게 null을 다르게 간주한다. 변수 선언시 물음표로 명시해야 한다.
 * -> var number3: Long? = null
 *
 * 코틀린에서 인스턴스 변수 생성시 new 키워드를 쓰지 않는다.
 *
 * 가변 불변, null 여부 지정해야 한다.
 *
 */

fun main() {
    var number1:Long = 10L
    var number2 = 10L
    var number3: Long? = null
}