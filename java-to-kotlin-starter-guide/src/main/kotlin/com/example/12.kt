package com.example

/**
 * 12. 코틀린에서 object 키워드를 다루는 방법
 * -> static 함수와 변수
 * 코틀린은 static이 없다 -> companion object { }로 구성 -> 클래스와 동행하는 유일한 오브젝트 (const val name -> 컴파일 시 변수에 할당, 정말 상수에만 붙인다.)
 *
 * -> 싱글톤
 * 클래스에 object를 붙여주면 된다. -> object Singleton { }
 * -> 익명 클래스
 * 일회성 사용
 *
 */

fun main() {
    PersonA.newBaby("ABC") // @JvmStatic 추가 필요
    PersonA.Factory.newBaby("ABC")

    Singleton.a += 10

    moveSomething(object : Movable {
        override fun move() {
            println("움직이자~")
        }

        override fun fly() {
            println("날자~")
        }
    })
}


class PersonA private constructor(
    var name: String,
    var age: Int
) {
    companion object Factory : Log {
        const val MIN_AGE = 0

        @JvmStatic
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("펄슨 클래스 동행 객체 Factory입니다.")
        }
    }
}

interface Log {
    fun log()
}

object Singleton {
    var a: Int = 0
}

interface Movable {
    fun move()
    fun fly()
}

private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}