package com.example.inflearnkotlinadvanced

import kotlin.system.measureTimeMillis

class Lec24 {

}

fun main() {
    TODO("main 함수 구현")
    repeat(3) {
        println("hello world")
    }

    val timeMillis = measureTimeMillis {
        val a = 1
        val b = 2
        val result = a + b
    }

    val result: Result<Int> = runCatching { 1 / 0 }
    result.getOrThrow()


}

fun acceptOnlyTwo(num: Int) {
    // 아래 코드와 유사
    require(num == 2) { "2만 허용!" }

//    if (num != 2) {
//        throw IllegalArgumentException("2만 허요ㅇ!")
//    }
}

private class Person1 {
    val status: PersonStatus = PersonStatus.PLAYING

    fun sleep() {
        // 아래 코드와 유사
        check(this.status == PersonStatus.PLAYING) { "msg" }

//        if (this.status != PersonStatus.PLAYING) {
//            throw IllegalArgumentException("2만 허요ㅇ!")
//        }
    }

    enum class PersonStatus {
        PLAYING,
        SLEEPING,
    }
}