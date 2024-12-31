package com.example.javatokotlinstarterguide

import java.util.Objects

/**
 * 3. 코틀린에서 Type을 다루는 방법
 * - 기본 타입
 * 선언된 기본 값을 보고 컴파일러가 타입을 추론한다. (int(기본), long - l, float - f, double(기본))
 * 자바 처럼 큰 타입으로 암시적 변경 X, 직접 명시해야 한다. -> 변수.toLong()
 * 그렇다면 인스턴스 변수 타입 변환은 어떻게 할까?
 * -> is 명령어 사용 -> 캐스팅 없이 추론 가능해지기 때문에 바로 인스턴스 변수로 사용 가능, not is = !is
 * -> as 명령어로 타입 변환 -> null 대비하여 as? 로 사용 가능 null일 경우 null이 값으로 들어간다.
 *
 * - 타입 캐스팅
 * - 코틀린의 3가지 특이한 타입
 * 1. Any
 * -> 자바의 Object 역할
 * -> 자바에서 원시 타입은 최상위 타입이 Object가 아니지만, 코틀린에서는 모두 ref Type이기 때문에 모두 Any를 최상위로 갖는다.
 * -> equals, hashCode 존재
 *
 * 2. Unit
 * -> 자바의 void 역할
 * -> void와 다르게 Unit 그 자체로 타입 인자로 사용 가능
 * -> 함수형 프로그래밍 Unit -> 단 하나의 인스턴스만 갖는 타입을 의미, 실제 존재 타입
 *
 * 3. Nothing
 * -> 함수가 정상적으로 끝나지 않는다는 것을 표현
 * -> 즉 무조건 예외 반환 또는 무한 루프 함수
 *
 * 4. String Interpolation, String Indexing
 * -> 문자열 안에서 ${변수} 또는 $변수 사용 가능 -> ${변수} 사용 권장
 * -> 큰 따움표 3개를 앞뒤로 감싸면 안에 내용을 자유롭게 개행 및 변수들을 추가하며  사용 가능하다.
 * -> 문자열도 str[0] or str.get(0)으로 조회 가능
 *
 * 초기 값을 보고 타입 추론이 가능하며 타입 캐스팅은 명시적이어야 한다.
 *
 */

fun main() {
    val number1 = 3
    //val number2: Long = number1
    val number2: Long = number1.toLong()

    val number3 = 3.0
    val number4: Float = number3.toFloat()

    println("하이요1 ${number1}")
    println("하이요2 $number1")

    test4()
}

fun test1(obj: Any ) {
    if (obj is Objects) {
        val obj1 = obj
        println(obj.equals("asdasd"))
        println(obj1.equals("asdasd"))
    }
}

fun test2(obj: Any?) {
    val a = obj as? Object
    a?.equals("asdasd")
}

fun test3():String {
    val age:Long = 13L

    return """
        하이요 ㅎㅎㅎㅎ
        age
        $age
        ${age}
    """.trimIndent()
}

fun test4() {
    val str = "ABCDE"
    println(str[0])
    println(str.get(0))

}
