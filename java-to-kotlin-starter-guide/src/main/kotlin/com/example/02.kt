package com.example.javatokotlinstarterguide

/**
 * 2. 코틀린에서 null을 다루는 방법
 * -> 코틀린에서 null 체크
 * -> safe Call, Elvis 연산자
 * safe Call -> str?.length -> 앞 변수 not null -> 그대로 실행, null -> null
 * Elvis 연산자-> ?: true -> 앞 변수가 not null -> 그대로 실행, null -> 대체 값 또는 throw Exception
 *
 * -> not null 선언
 * !! -> str!!.startsWith("A") -> null이 아니라고 강조해서 컴파일 에러를 무시하고 사용 가능하지만, 실제 null이 들어온 경우 런타임 예외가 발생한다.
 *
 * -> 플랫폼 타입
 * 코틀린에서 자바의 @NotNull, @Nullable 붙어있는 되어있는 메서드 호출시 애노테이션에 따라 null이 아니라는 가정하에 동작 할 수도 있다. -> 애노테이션을 코틀린이 이해한다. (플랫폼 타입)
 * -> 코틀린이 null 관련 정보를 알 수 없는 경우 런타임 예외 발생 가능 / 자바 메서드 호출시 Null 정보가 없네? -> 일단 동작하게 해줄게
 * -> 자바 코드를 살펴보거나, 코틀린으로 래핑해 방어 로직 추가
 *
 */

fun main() {

}

fun test1(str: String?): Boolean {
    //return null
    return true
}

fun test2(str: String?): Boolean? {
    return null
}

fun test3(str: String?): Boolean {
    //return str.startsWith(str)
    return str?.startsWith(str) ?: true
}

fun test4(str: String?): Boolean {
    return str!!.startsWith("A")
}