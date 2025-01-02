package com.example.calculator

class Calculator(
    var number: Int
//    private var _number: Int
) {
//    val number: Int
//        get() = this._number

    fun add(operand: Int) {
        this.number += operand
    }

    fun minus(operand: Int) {
        this.number -= operand
    }

    fun multiply(operand: Int) {
        this.number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("${operand}으로 나눌 수 없습니다.")
        }
        this.number /= operand
    }
}