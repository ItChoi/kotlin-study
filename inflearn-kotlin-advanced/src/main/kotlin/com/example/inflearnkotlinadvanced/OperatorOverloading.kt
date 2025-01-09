package com.example.inflearnkotlinadvanced

import java.time.LocalDate

class OperatorOverloading {

}

data class Point(
    val x: Int,
    val y: Int,
) {
    fun zeroPointSymmetry(): Point = Point(-x, -y)

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    operator fun inc(): Point {
        return Point( x + 1, y + 1)
    }

}

fun main() {
    var point = Point(20, -10)
    println(point.zeroPointSymmetry())
    println(-point)
    println(++point)

    println(LocalDate.of(2023, 1, 1).plusDays(3)) // 3일이 지난 일자
    println(LocalDate.of(2023, 1, 1) + Days(3)) // 오버로딩 plus 함수 호출
    println(LocalDate.of(2023, 1, 1).plus(Days(3))) // 오버로딩 plus 함수 호출
    println(LocalDate.of(2023, 1, 1) + 3.d)
}

data class Days(val day: Long)

val Int.d: Days
    get() = Days(this.toLong())

operator fun LocalDate.plus(days: Days): LocalDate {
    println("test@@")
    return this.plusDays(days.day)
}
