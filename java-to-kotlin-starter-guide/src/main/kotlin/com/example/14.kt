package com.example

/**
 * 14. 코틀린에서 다양한 클래스를 다루는 방법
 * -> Data Class
 * 계층간 데이터 전달을 위한 DTO
 * 데이터(필드), 생성자, getter, equals, hashCode, toString 등의 다양한 함수를 갖고 있다.
 * class 앞에 data만 붙이면 기본적인 함수(위)를 만들어준다.
 * Java jdk 16부터 data와 비슷한 record 클래스 도입
 *
 * -> Enum Class
 * 추가적인 클래스 상속 불가
 * 인터페이스 구현 가능
 * 각 코드는 싱글톤이다.
 * enum으로 분기 로직을 작성하게 된 경우, 추가되는 코드에 대한 분기 코드를 계쏙 추가해줘야 한다.
 * 코틀린 enum은 IDE 설정에 따라 분기문에 enum 추가시 반영을 안 한 경우 warning 또는 컴파일 에러를 줄 수 있다.
 * 즉 컴파일러가 enum의 모든 타입을 알고 있어, else를 작성하지 않아도 될 뿐만 아니라 코드 추가에 대한 대응이 수얼하다.
 *
 * -> Sealed Class, Sealed Interface
 * "봉인을 한" 의미를 갖고 있다.
 * 상속 가능 추상 클래스이자 외부 클래스는 상속 불가 -> 계층 구성은 가능하지만, 외부 클래스 상속을 막는다
 * 즉 하위 클래스를 봉인한다.
 *
 * 컴파일 타임에 하위 클래스 타입을 모두 기억한다. -> 런타임시 클래스 타입 추가 불가
 * 하위 클래스는 같은 패키지에 있어야 한다.
 *
 * 클래스 상속 가능, 하위 클래스는 멀티 인스턴스 가능
 *
 * 추상화가 필요한 Entity, DTO에 Sealed Class 활용 할 수 있다.
 * jdk 17에도 Sealed 클래스가 도입됐다.
 *
 */

fun main() {
    val dto1 = PersonDto("최태현", 100)
    println(dto1.age)
}

data class PersonDto(
    val name: String,
    val age: Int
)

enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US")
}

fun test1(country: Country) {
    when (country) {
        Country.KOREA -> "TODO()"
        Country.AMERICA -> "TODO()"
    }
}

