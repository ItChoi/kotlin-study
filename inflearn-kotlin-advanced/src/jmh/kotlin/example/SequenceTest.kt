package example

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@State(Scope.Benchmark) // 벤치마크를 한다 정도로만 이해
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class SequenceTest {
    private val fruits = mutableListOf<Fruit>()

    // Sequence, Iterable 실행전 호출
    @Setup
    fun init() {
        (1..2000000).forEach { _ -> fruits.add(Fruit.random()) }
    }

    // 벤치마크 대상 함수
    @Benchmark
    fun kotlinSequence() {
        val arg = fruits.asSequence()
            .filter { it.name == "사과" }
            .map { it.price }
            .take(10000)
            .average()
    }

    // 벤치마크 대상 함수
    @Benchmark
    fun kotlinIterable() {
        val arg = fruits
            .filter { it.name == "사과" }
            .map { it.price }
            .take(10000)
            .average()
    }
}

data class Fruit(
    val name: String,
    val price: Long, // 1000 ~ 20000
) {
    companion object {
        private val NAME_CANDIDATES = listOf("사과", "바나나", "수박", "체리", "오렌지")
        fun random(): Fruit {
            val randNum1 = Random.nextInt(0, 5)
            val randNum2 = Random.nextLong(0, 20001)
            return Fruit(
                name = NAME_CANDIDATES[randNum1],
                price = randNum2,
            )
        }
    }
}