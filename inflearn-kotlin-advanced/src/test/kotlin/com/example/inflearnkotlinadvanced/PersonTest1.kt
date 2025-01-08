package com.example.inflearnkotlinadvanced

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonTest1 {
    private val person = Person()
    @Test
    fun isKimTest() {
        //given
        //val person = Person("김수한무")
        val person = person.apply { name = "김수한무" }

        //when & then
        assertThat(person.isKim).isTrue()
    }

    @Test
    fun maskingNameTest() {
        //given
        //val person = Person("최태현")
        val person = person.apply { name = "최태현" }

        //when & then
        assertThat(person.maskingName).isEqualTo("최**")
    }
}