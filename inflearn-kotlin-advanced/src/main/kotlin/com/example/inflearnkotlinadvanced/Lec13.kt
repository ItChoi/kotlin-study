package com.example.inflearnkotlinadvanced

class Lec13 {

}

fun main() {
    // 고차 함수 사용: 람다식
    //compute(5, 3, { a, b -> a + b })
    //compute(5, 3) { a, b -> a + b } // 함수 마지막 파라미터가 함수인 경우 함수 호출부 바깥으로 뺼 수 있다.

    // 고차 함수 사용: 익명 함수
    //compute(5, 3, fun(a: Int, b: Int) = a + b) // 익명함수 -> 함수명 필요 X, body를 expression으로 대입
//    compute(5, 3, fun(a, b) = a + b) //  명함수 -> 제일 짧게 작성, 타입은 컴파일러가 유추 가능해서 생략 가능하다.
//    compute(5, 3, fun(a: Int, b: Int): Int { // 익명함수 -> 함수명 필요 X, body를 일반적인 함수로
//        return a + b
//    })

    /*iterate(
        listOf(1, 2, 3, 4, 5),
        fun(num) {
            if (num == 3) {
                return
            }
        println(num)
        }
    )*/

    // 람다에서 return 사용 불가
    iterate(listOf(1,2,3,4,5)) { num ->
        if (num != 3) {
            println(num)
            //return // 가장 가까운 fun 키워드를 종료하는 기능 -> 현재 시점 main 함수 종료 (비지역적 반환, non-local return)
        }
        println("ABC")

    }

}

private fun compute(
    num1: Int,
    num2: Int,
    op: (Int, Int) -> Int
): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

fun calculate(num1: Int, num2: Int, oper: Char): Int {
    return when (oper) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> {
            if (num2 == 0) {
                throw IllegalArgumentException("0으로 나눌 수 없습니다!")
            } else {
                num1 / num2
            }
        }
        else -> throw IllegalArgumentException("들어올 수 없는 연산자(${oper}입니다!")
    }
}

fun calculate1(num1: Int, num2: Int, oper: Operator) = oper.calcFun(num1, num2)

enum class Operator(
    private val oper: Char,
    val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('*', { a, b -> a * b }),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다!")
        } else {
            a / b
        }
    }),
}