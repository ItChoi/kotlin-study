package com.example

/**
 * 8강. 코틀린에서 함수를 다루는 방법
 * -> 함수 선언 문법
 * 함수를 중괄호로 감싸지 않고 내용을 바로 대입해서 사용 가능 (test01())
 *
 * -> default parameter
 * 자바는 오버로딩 메서드를 사용 할 수 있다.
 * 코틀린은 repeat(a, b, c) 메서드 처럼 사용 가능
 * 파라미터에 대입연산자를 통해 값이 안 들어온 경우 디폴트 값 설정
 * 중간중간 일부 파라미터만 값을 넣고 싶은 경우, 매개변수 이름을 통해 직접 지정해 값을 넣고, 지정되지 않은 값은 default 값을 사용한다.
 * builder 장점을 갖는다.
 *
 *
 * -> named argument (parameter)
 * test022(a, b) 메서드 참고
 * 코틀린 -> 자바 함수 호출시 해당 기능 사용 불가
 *
 * -> 같은 타입의 여러 파라미터 받기 (가변인자)
 * 자바 가변인자 -> 메서드(String ... str)
 * 코틀린 가변인자 -> 메서드(vararg strings: String), 메서드 호출시 가변인자 표시 '*'를 붙여야 한다. 메서드(*strings)
 *
 */

fun main() {

}

fun test01(a: Int, b: Int): Int =
    if (a > b) {
        a
    } else {
        b
    }

// 타입 추론 가능, 간단 로직은 중괄호 생략 가능
fun test011(a: Int, b: Int) = if (a > b) a else b

fun repeat(
    str: String,
    num: Int = 3, useNewLine: Boolean = true
): Unit {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun test022() {
    test02("피카츄")
    test02(name = "피카츄", gender = "여성")
}

fun test02(name: String, gender: String = "남성") {
    println(name)
    println(gender)
}

fun test033() {
    //val strings = listOf("A", "B", "C") // list 자료 구조는 가변인자 안 되는듯
    val strings = arrayOf("A", "B", "C")
    //test03(strings) // 컴파일 에러
    test03(*strings)
}
fun test03(vararg strings: String) {
    for (string in strings) {
        println(string)
    }
}