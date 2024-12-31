package com.example

/**
 * 5. 코틀린에서 제어문을 다루는 방법
 * -> if문
 *
 * -> Expression과 Statement
 * - Statement -> 프로그램의 문장, 하나의 값으로 도출되지 않는다.
 *   - Expression -> 하나의 값으로 도출되는 문장
 * 코틀린에서 if else는 Expression이다. 즉 바로 return 가능 (return if ...)
 * 따라서 코틀린에서 삼항 연산자는 없다. (쓸 필요가 없다)
 *
 *
 * -> switch와 when
 * switch case가 없다. switch 자리에 when을 쓴다.
 * 다양한 조건으로 분기 가능하다.
 * 조건부 -> 모든 expression 사용 가능
 * 다양한 조건부 사용으로 활용도가 높지만, enum, sealed 클래스와 사용시 더욱 다양한 사용이 가능하다.
 */

fun main() {

}

fun test01(score: Int) {
    if (score < 0) {
        // new를 붙이지 않는다.
        throw IllegalArgumentException("${score}는 0보다 작을 수 없다.")
    }
}

fun test02(score: Int): String {
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
}

fun test03(score: Int): String {
    return if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
}

fun test031(score: Int): String {
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

fun test04(score: Int) {
    if (score !in 0..100) {
        throw java.lang.IllegalArgumentException("${score}범위는 0 ~ 100입니다.")
    }

    if (score in 0..100) {
        throw java.lang.IllegalArgumentException("${score}범위가 0 ~ 100 사이 입니다.")
    }
}

fun test05(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun test051(score: Int): String {
    return when (score / 10) {
        in 90..99 -> "A"
        in 80.. 89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}
fun test06(obj: Any): Boolean {
    return when(obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun test07(number: Int) {
    when (number) {
        1, 0, -1 -> println("이 숫자에 포함된 수 입니다.")
        else -> println("그 외 입니다.")
    }
}

fun test08(number: Int) {
    when {
        number == 0 -> println("0")
        number % 2 == 0 -> println("짝수")
        else -> println("홀수")
    }
}