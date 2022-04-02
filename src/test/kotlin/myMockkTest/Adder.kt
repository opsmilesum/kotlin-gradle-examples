package myMockkTest

class Adder(private val a: Int = 0) {
    constructor(x: String) : this(x.toInt())
    fun addOne(num: Int) = num + 1
}