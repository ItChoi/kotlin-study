package com.example

/**
 * 20. 코틀린의 scope function
 * -> scope function이란 무엇인가?!
 * scope: 영역 / function: 함수 -> 일시적인 영역을 형성하는 함수
 * 메서드 체이닝 용도로 활용
 *
 * -> scope function의 분류
 * - 다섯 가지 종류
 * - let: 확장 함수임, 람다의 결과 반환 (람다 안 it 사용)
 * - run: 확장 함수임, 람다의 결과 반환 (람다 안 this 사용)
 * - also: 확장 함수임, 객체 그 자체 반환 (람다 안 it 사용)
 * - apply: 확장 함수임, 객체 그 자체 반환 (람다 안 this 사용)
 * - with: 확장 함수 X, with(파라미터, 람다)
 *
 * -> 언제 어떤 scope function을 사용해야 할까?!
 * - let ->
 *      - 하나 이상의 함수를 call chain 결과로 호출 할 때
 *      - not-null 값에 대해서만 code block을 실행시킬 때
 *      - 일회성으로 제한된 영역에 지역 변수 만들 때
 * - apply ->
 *      - 객체 설정시 객체 수정 로직이 call chain 중간에 필요할 때 (Test Fixture 만들 때)
 * - also ->
 *      - 객체 수정 로직이 call chain 중간에 필요할 때
 * - with ->
 *      - 객체를 다른 객체로 변환 할 때 모듈간 의존성 때문에 정적 팩토리 혹은 toClass 함수를 만들기 어려울 때
 *
 * -> scope function과 가독성
 * 숙련된 코틀린 개발자만 읽을 수 있는 코드는 지양해야 한다.
 * 디버깅이 쉬운 코드를 지향하자.
 * 수정이 더 쉬운 코드를 지향하자.
 */

fun main() {
    mutableListOf("one", "two", "three")
        .also { println("hahaha: $it") }
        .add("four")
    println("-----------------")
    val numbers = mutableListOf("one", "two", "three")
    println("hahaha: $numbers")
    numbers.add("four")
}

private fun test01(person: Person?) {
    if (person != null) {
        println(person.name)
        println(person.age)
    }
    // 위 코드를 아래와 코드로 변경 가능, 일시적인 영역을 만들어 활용
    // safe call 사용
    // let -> scope function의 한 종류
    // 람다 안에서 it을 통해 들어온 파라미터로 접근 가능
    person?.let {
        println(it.name)
        println(it.age)
    }
}