package com.example.inflearnkotlinadvanced

class Lec14 {
}

fun main() {
    compute(5, 3, { num1, num2 -> num1 * num2 })

    var num = 5
    num += 1
    val plusOne: () -> Unit ={ num += 1 }
}

private fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}