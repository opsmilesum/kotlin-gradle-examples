package generics

class OutTest<out T>(val value: T) {
    fun foo(): T {
        return value;
    }
}

class InTest<in T>() {
    fun foo(value: T) {
        println("In $value")
    }
}

fun main() {
    println(OutTest(3).foo())
    println(InTest<String>().foo("Three"))
}