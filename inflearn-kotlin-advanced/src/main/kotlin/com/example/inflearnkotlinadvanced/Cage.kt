package com.example.inflearnkotlinadvanced

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))
    //val carp: Carp = cage.getFirst() as Carp // 타입 캐스팅 -> 위험한 코드다 -> 첫 번째 코드가 Carp가 아니라면? 또는 수정되었다면? 런타임 에러가 된다.
    val carp: Carp = cage.getFirst() as? Carp
        ?: throw IllegalArgumentException()


    val cage2 = Cage2<Carp>()
    cage2.put((Carp("잉어")))
    val carp2: Carp = cage2.getFirst()


    // 불가능
    val goldenFishCage = Cage2<GoldFish>()
    goldenFishCage.put(GoldFish("금붕어"))
    val fish = Cage2<Fish>()
    fish.moveFrom(goldenFishCage) // 컴파일 에러, fun moveFrom(cage: Cage2<out T>) { } out을 붙여주면 컴파일 에러가 사라진다.

    // 가능
    val cage3 = Cage2<Fish>()
    fish.put(GoldFish("금붕"))
    fish.put(Carp("잉잉어"))

    println(fish)

}

class Cage {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage) {
        this.animals.addAll(cage.animals)
    }
}