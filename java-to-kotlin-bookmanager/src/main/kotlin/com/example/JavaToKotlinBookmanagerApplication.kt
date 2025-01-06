package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JavaToKotlinBookmanagerApplication

fun main(args: Array<String>) {
    // 확장 함수가 미리 만들어져 있다.
    // *args -> 스프레드 연산자(가변 인자)
    runApplication<JavaToKotlinBookmanagerApplication>(*args)
}