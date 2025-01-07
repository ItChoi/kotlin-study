package com.example.inflearnkotlinadvanced

fun main() {
    val fishCage = Cage3<Fish>()
    val animalCage: Cage3<Animal> = fishCage

}

// 클래스 자체를 공변하게 만든다.
class Cage3<out T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return this.animals.first()
    }

    fun getAll(): List<T> {
        return this.animals
    }
}