package com.example.inflearnkotlinadvanced

import kotlin.reflect.KClass

//@Retention(AnnotationRetention.RUNTIME)
//@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Repeatable
annotation class Shapre(
    val text: String,
    val number: Int,
    val clazz: KClass<*>, // 코드로 작성한 클래스를 표현한 클래스
)

class Annotation {
}

fun main() {
    val kClass: KClass<Annotation> = Annotation::class
}

