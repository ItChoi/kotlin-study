package com.example

/**
 * 19. 코틀린의 이모저모
 * -> Type Alias와 as import
 * Type Alias -> typealias FruitFilter = (Fruit19) -> Boolean 선언 후 사용
 * as import -> 다른 패키지 동일한 클래스명이 있는 경우 별칭을 줘서 별칭으로 접근해 사용한다.
 * - import com.testA.String as StringA
 * - import com.testB.BBB.String as StringB
 *
 * -> 구조분해와 componentN 함수
 * 구조분호 -> 복합적인 값 분해 후 여러 변수 한 번에 초기화
 * N은 숫자 -> 몇 번 째 컴포넌트를 가져오는지 명시
 * data class -> componentN 함수 자동으로 만들어준다. (Person19 클래스 참고)
 * not data class -> componentN 함수 직접 구현 필요 (Person1901 클래스 참고)
 *
 * ``` data class
 * val person = Person19("최태현", 100)
 * val (name, age) = person
 * for ((key, value) in map.entires) { } 등의 코드도 구조분해 문법이다.
 *
 * // val (name, age) = person 코드는 사실상 아래와 같은 코드
 * val name = person.component1()
 * val age = person.component2()
 * ```
 *
 * -> Jump와 Label
 * Label 사용 Jump -> 사용하지 않는 것이 좋다.
 *
 * for-each문에 continue, break가 동작하지 않는다. -> 써야되는 경우 기법이 있다.
 * - run 블록으로 감싸기
 * - break -> return@run, 이중 forEach를 쓴 경우 가장 바깥쪽 for문이 아닌 forEach를 아예 종료 해버릴 수도 있다.
 * - continue -> return@forEach
 * - break, continue 사용시 for문 사용 권장
 *
 * -> TakeIf와 TakeUnless
 * if else문 -> takeIf 사용 가능 (takeIf, takeUnless 메서드 참고)
 * takeIf -> 주어진 값 만족시 그 값 반환, 아닌 경우 null 반환
 * TakeUnless -> 주어진 값 불만족시 그 값 반환, 아닌 경우 Null 반환
 *
 *
 */

fun main() {
    // data class인 경우 바로 접근 가능
    val person = Person19("최태현", 100)
    val (name, age) = person // name, age를 인식하는 것은 아니다. 필드 순서대로 나온다
    val nameWithDirect = person.component1()
    val ageWithDirect = person.component2()

    println(name)
    println(age)
    println("나이: ${age}, 성명: ${name}")

    val person1901 = Person1901("아무개", 910)
    val (name1901, age1901) = person1901
}

// 타입 대신 표현
typealias FruitFilter = (Fruit19) -> Boolean
private fun test01(
    fruits: List<Fruit19>,
    filter: FruitFilter
) {

}

data class Fruit19(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {

}

data class Person19(
    val name: String,
    val age:Int,
)

class Person1901(
    val name: String,
    val age:Int,
) {
    // 연산자 오버로딩으로 간주 -> operator 키워드 추가 필요
    operator fun component1(): String {
        return this.name
    }

    // 연산자 오버로딩으로 간주 -> operator 키워드 추가 필요
    operator fun component2(): Int {
        return this.age
    }
}

private fun takeIf(number: Int): Int? {
    return number.takeIf { it > 0 }
}
private fun takeUnless(number: Int): Int? {
    return number.takeUnless { it > 0 }
}

