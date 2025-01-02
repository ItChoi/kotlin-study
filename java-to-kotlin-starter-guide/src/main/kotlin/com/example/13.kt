package com.example

/**
 * 13. 코틀린에서 중첩 클래스를 다루는 방법
 * -> 중첩 클래스의 종류
 * static 사용 중첩 클래스 -> 밖 클래스 직접 참조 불가
 * static 미사용 주첩 클래스 -> 내부 클래스, 지역 클래스, 익명 클래스
 *
 * 내부 클래스는 숨겨진 외부 클래스 정보를 갖고 있다 -> 참조 해지 못 하는 경우 메모리 누수 발생 가능성과 디버깅이 어렵다.
 * 내부 클래스의 직렬화 형태가 명확하지 않아 직렬화에 제한이 있다.
 * 클라스 안에 클래스 사용시 static 클래스 사용 권장
 *
 *
 * -> 코틀린의 중첩 클래스와 내부 클래스
 * 예시 코드 참조
 * inner 명령어 명시하여 @로 참조
 *
 */

fun main() {

}

class House(
    val address: String,
    val livingRoom: LivingRoom1
) {
    // 그냥 클래스 작성시 -> 바깥 클래스와 연결이 없는 클래스가 만들어진다.
    class LivingRoom1(
        private val area: Double
    )

    // 바깥 클래스와 연결된 inner class
    inner class LivingRoom2(
        private val area: Double
    ) {
        val address: String
            // 바깥 클래스 참조
            get() = this@House.address
    }
}