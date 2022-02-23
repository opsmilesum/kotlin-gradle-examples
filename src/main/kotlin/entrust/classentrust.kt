package entrust

interface Base {
    fun print() {
        println("Printed from Base")
    }
}

class BaseImpl : Base {
    override fun print() {
        println("Printed from Base Impl")
    }
}

class BaseImpl2 : Base {
    override fun print() {
        println("Printed from Base Impl2")
    }
}

class Derived(b: Base) : Base by b

fun main() {
    Derived(BaseImpl()).print()
    Derived(BaseImpl2()).print()
}

