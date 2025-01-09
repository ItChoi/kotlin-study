package com.example.inflearnkotlinadvanced

fun main() {
    val userId = 1L
    val bookId = 2L
    handle1(
        userId = userId,
        bookId = bookId,
    )

    val inlineUserId = Id<User2>(1L)
    val inlineBookId = Id<Book2>(1L)
    handle2(inlineUserId, inlineBookId)

    // try catch 중복 예외 방법1
    try { } catch (e: Exception) {
        when (e) {
            is AException,
            is BException -> TODO()
            is CException -> TODO()
        }
        throw e
    }
}

@JvmInline
value class Key(val key: String)

class User1(
    val id: Long,
    val name: String,
)

class Book1(
    val id: Long,
    val author: String,
)

class User2(
    val id: Id<User2>,
    val name: String,
)

class Book2(
    val id: Id<Book2>,
    val author: String,
)

fun handle1(userId: Long, bookId: Long) {

}

fun handle2(userId: Id<User2>, bookId: Id<Book2>) {

}

@JvmInline
value class Id<T>(val id: Long)

@JvmInline
private value class Number(val num: Long) {
    init {
        require(num in 1..10)
    }
}

fun logic(n: Int) {
    when {
        n > 0 -> throw AException()
        n == 0 -> throw BException()
    }
    throw CException()
}

class AException : RuntimeException()
class BException : RuntimeException()
class CException : RuntimeException()