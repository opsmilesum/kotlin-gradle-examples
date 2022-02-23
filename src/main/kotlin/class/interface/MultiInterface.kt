package `class`.`interface`

interface A {
    fun callA() {
        println("Call from A")
    }
}

interface B {
    fun callB() {
        println("Call from B")
    }

}

class C : A, B

fun main() {
    val c: C = C();
    c.callA()
    c.callB()
}

/**
Call from A
Call from B
 * */