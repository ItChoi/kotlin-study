package com.example

/**
 * 9. 코틀린에서 클래스를 다루는 방법
 * -> 클래스와 프로퍼티
 * -> 생성자와 init
 * -> 커스텀 getter, setter
 * -> backing field
 */

fun main() {
    val person = Person("피카츄", 100)
    println(person.age) // 바로 필드에 접근 가능 (getter 호출)
    person.age = 13 // 바로 변경 가능 (setter 호출)
    println(person.age)
}

class Person1 constructor(name: String, age: Int){
    val name: String = name
    var age: Int = age
}

// 타입 추론 가능, 생성자 생략 가능
class Person2(name: String, age: Int){
    val name = name
    var age = age
}

// class body 로직이 없다면 중괄호 생략 가능
class Person3(
    val name: String,
    var age: Int
)

// 주 생성자 -> 반드시 존재 필요, 부 생성자보다 기본 생성자에 디폴트 값 사용을 권장한다.
// 부 생성자 사용 할일 거의 X, converting 등의 이유로 부 생성자를 사용 할 수 있지만, 이또한 부 생성자 활용보단 정적 팩토리 메서드 사용 추천
class Person(
    val name: String,
    var age: Int
) {
    // 클래스 초기화 시점에 한 번 호출되는 init -> 검증 가능
    init {
        if (age < 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없다.")
        }
    }

    // 매개변수가 다른 생성자 로직 추가
    // 부 생성자(선택적으로 만들면 된다.) -> 최종적으로 주 생성자를 호출해야 함
    constructor(name: String): this(name, 1) {
        println("첫 번 째 부생성자")
    }

    constructor(): this("홍길동") {
        println("두 번 째 부생성자")
    }

    fun isAduit(): Boolean {
        return this.age >= 20
    }

    val isAduit123: Boolean
        get() = this.age >= 20

    val isAduit1: Boolean
        get() {
            return this.age >= 20
        }

    // getter 무한 루프 호출 -> field를 통해 제어 (backing field)
//    val name1: String = name
//        get() = name.uppercase()
    val name1: String = name
        get() = field.uppercase() // field -> 생성자에서 받은 name을 가르킨다

    // 이렇게 쓰면 backing field를 쓸 일이 없을 수 있다.
    val upperCaseName: String
        get() = this.name.uppercase()
}

