package com.example

/**
 * 10. 코틀린에서 상속을 다루는 방법
 * -> 추상 클래스
 * - Animal
 *   - Cat
 *   - Penguin
 * 자바, 코틀린 모두 추상 클래스는 인스턴스화 할 수 없다.
 *
 * -> 인터페이스
 * - Flyable (interface)
 *   - Penguin
 * - Animal (abstract class)
 *   - Penguin
 * - Swimmable (Interface)
 *   - Penguin
 *
 * -> 클래스를 상속할 때 주의할 점
 * type과 마찬가지로 : 활용, 컨벤션은 다르다. 한 칸 띄고 콜론
 * 상위 하위 클래스 init 사용시 open 사용시 주의 -> 초기화 값이 잘못 출력 될 수 있다.
 *
 *
 * -> 상속 관련 지시어 정리
 */

// 상속 클래스 생성자 바로 호출 필요
class Cat(
    species: String
) : Animal(species, 4) {
    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~")
    }
}

class Penguin1(
    species: String
) : Animal(species, 2) {
    private val wingCount: Int = 2
    override fun move() {
        println("펭귄이 움직입니다~~@")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount
}

class Penguin(
    species: String
) : Animal(species, 4), Swimable, Flyable {
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 움직인다 꽥꽥")
    }

    override val swimAbility: Int
        get() = 3
    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Flyable>.act()
        super<Swimable>.act()
    }
}

abstract class Animal(
    protected val species: String,
    protected open val legCount: Int // 프로퍼티 override시 open 키워드 사용
) {
    abstract fun move()
}

interface Flyable {
    fun act() {
        println("파닥파닥")
    }
}
interface Swimable {
    val swimAbility: Int
        get() = 3

    fun act() {
        println(swimAbility)
        println("어푸어푸")
    }
}
