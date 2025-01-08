package com.example.inflearnkotlinadvanced

fun main() {
    //val cage1 = Cage5<Animal>()
    // val cage2 = Cage5<Int>() // 컴파일 에러
    var cage3 = Cage5<Bird>()
    val cage4 = Cage5<Bird>(mutableListOf(Sparrow(), Eagle()))
    cage4.printAfterSorting()

    //var cage5 = Cage2<Fish?>()


}

abstract class Bird(
    name: String,
    private val size: Int,
) : Animal(name), Comparable<Bird> {
    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

class Sparrow : Bird("참새", 100)
class Eagle : Bird("독수리", 500)


class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf()
) where T : Animal, T : Comparable<T> {

    fun printAfterSorting() {
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage5<out T>) {
        val first = getFirst() // 가능
        // put(Carp("잉잉이어어")) // 불가능
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage5<T>): Unit {
        otherCage.animals.addAll(this.animals)
    }
}

fun <T> List<T>.hasIntersection(other: List<T>): Boolean {
    //intersect -> infix 함수 중위 함수
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}