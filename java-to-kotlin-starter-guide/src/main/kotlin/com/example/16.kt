package com.example

/**
 * 16. 코틀린에서 다양한 함수를 다루는 방법
 * -> 확장함수
 * 코틀린은 자바와 100% 호환을 목표로 한다. -> 자바 코드 위에 코틀린 코드를 추가 할 수 없을까? -> 메서드는 클래스 바깥에 있지만, 클래스 안에 있는 것 처럼 호출하여 사용 가능
 * 확장 함수는 대상 클래스의 private, protected 멤버를 가져올 수 없다.
 * 확장 함수, 멤버함수 동일한 메서드가 존재한다면 -> 멤버함수가 우선시 호출된다.
 * 상속 구조에서 확장 함수를 오버라이딩한 경우, 선언 타입에 따라 타입에 해당하는 확장 함수가 호출된다.
 *
 * -> infix 함수
 * 중위함수, 함수 호출 새로운 방법
 * downTo, step -> 중위 함수 (변수 + 함수이름 + arg) -> ex) 3.add2(4)
 * infix fun Int.add2(val: Int): Int { }
 *
 * -> inline 함수
 * 함수 호출 대신, 함수 본문을 그대로 복붙해서 사용(로직 자체가 copy되어 사용된다)
 * 오버헤드 줄일 때 사용, 성능 측정을 통해 신중하게 사용해야 한다.
 *
 * -> 지역함수
 * 함수 안에 함수 선언해 사용 -> 중복 코드 처리 등
 * 함수 추출 필요한데, 일단은 내부 함수에서 사용 할 때 -> depth가 깊어지고 깔끔하지 않아 잘 사용하지 않는다.
 *
 */

fun main() {
    var str = "ABC"
    println(str.lastChar())
    println(str.lastChar1)


}

// String 클래스 확장 -> fun 확장하려는클래스.함수이름(파라미터): 리턴타입, this를 이용해 실제 클래스 안 값에 접근
private fun String.lastChar(): Char {
    return this[this.length - 1]
}

private val String.lastChar1: Char
    get() = this[this.length - 1]