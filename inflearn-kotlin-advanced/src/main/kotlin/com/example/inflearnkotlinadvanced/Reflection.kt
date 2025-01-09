package com.example.inflearnkotlinadvanced

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.cast
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation

fun main() {
    // executeAll(Reflection())
    val kClass1: KClass<Reflection> = Reflection::class

    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class

    //val kClass3: KClass<out Any> = Class.forName("reflect.Reflection").kotlin

    kClass1.java // Class<Reflection
    kClass1.java.kotlin  // KClass<Reflection

    val kType: KType = GoldFish1::class.createType()

    val goldFish = GoldFish1("금붕어")
    goldFish::class.members.filter { it.name == "name" } // 필드
    goldFish::class.members.filter { it.name == "print" }.first().call(goldFish) // 함수
    goldFish::class.members.first { it.name == "print" }.call(goldFish) // 함수

    executeAll(Reflection())
}

@Target(AnnotationTarget.CLASS)
annotation class Executable

@Executable
class Reflection {
    fun a() {
        println("A입니다")
    }

    fun b(n: Int) {
        println("B입니다")
    }
}

fun executeAll(obj: Any) {
    val kClass: KClass<out Any> = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }

    callableFunctions.forEach { function ->
        function.call(kClass.createInstance())
    }
}

class GoldFish1(val name: String) {
    fun print() {
        println("금붕어 이름은 ${name}입니다.")
    }
}

fun castToGoldFish1(obj: Any): GoldFish1 {
    //return obj as GoldFish1
    return GoldFish1::class.cast(obj) // 리플렉션 이용해 반환, obj를 GoldFish1로 캐스트
}