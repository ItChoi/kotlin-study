package com.example.calculator

import com.example.javatokotlinbookmanager.calculator.Calculator
import java.lang.IllegalStateException

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

class CalculatorTest {
    /**
     * given/when/that 패턴(?)
     * given-> 테스트 준비
     * that -> 호출 의미
     * when -> 호출 후 검증
     */

    fun addTest() {
        val calculator = Calculator(5)
        calculator.add(3)
        // data class, 생성자 파라미터가 private인 경우 아래와 같이 테스트 가능
        // data class가 아닌 경우 생성자 파라미터 접근 제어자를 public으로 테스트 가능
        // data class X, 생성자 파라미터 private -> backing field 사용, 코틀린 권장 사용법 -> 생성자 파라미터 시작을 _으로 시작 ex: _name, 지역 변수를 name으로 하고 getter만 허용한다.
//        val expectedCalculator = Calculator(8)
//        if (calculator != expectedCalculator) {
//            throw IllegalArgumentException()
//        }

        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }
    }

    fun minusTest() {
        val calculator = Calculator(5)
        calculator.minus(3)

        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun multiplyTest() {
        val calculator = Calculator(5)
        calculator.multiply(3)

        if (calculator.number != 15) {
            throw IllegalArgumentException()
        }
    }

    fun divideTest() {
        val calculator = Calculator(5)
        calculator.divide(2)

        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun divideExceptionTest() {
        //given
        val calculator = Calculator(5)

        //when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다.") {
                throw IllegalStateException("메시지가 다릅니다.")
            }
            // 테스트 성공
            return;
        } catch (e: Exception) {
            throw IllegalStateException()
        }

        throw IllegalStateException("기대한 예외가 발생하지 않았습니다.")
    }
}