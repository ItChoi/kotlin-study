package com.example

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * 7. 코틀린에서 예외를 다루는 방법
 * -> try catch finally 구문
 *
 *
 * -> checked Exception / Unchecked Exception
 * 메서드 옆 throw를 붙이지 않는다.
 * 즉 checked / unchecked 구분 X -> 모두 UnChecked Exception
 *
 * -> try with resources
 * 코틀린은 해당 구문이 없다.
 * io.use { } 구문 등을 통해 내부 close 함수가 적용 돼 있다.
 * 아직은 코틀린은 해당 구문 대신 use를 사용한다 정도만 이해하자.
 */

fun main() {

}

fun test001(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("주어진 ${str}은 숫자가 아닙니다.")
    }
}

fun test002(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        return null
    }
}

fun test0021(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun test003() {
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/a.txt")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}

fun test004(path: String) {
    BufferedReader(FileReader(path)).use { reader ->
        println(reader.readLine())
    }
}

