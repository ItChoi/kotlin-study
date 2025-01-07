package com.example.inflearnkotlinadvanced

fun main() {
    val fishCage = Cage2<Fish>()

    val goldFishCage = Cage2<GoldFish>()
    goldFishCage.put(GoldFish("금붕어쓰"))
    goldFishCage.moveTo(fishCage) // 반공변하게 만들어야 한다.

}

class Cage2<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage2<out T>) {
        val first = getFirst() // 가능
        // put(Carp("잉잉이어어")) // 불가능
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage2<in T>): Unit {
        otherCage.animals.addAll(this.animals)
    }
}