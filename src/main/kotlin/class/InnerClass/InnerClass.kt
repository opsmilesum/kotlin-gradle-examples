package `class`.InnerClass


class Outer {
    val info = "from Outer"

    class NestedClass {
        fun foo() = "NestedClass foo"
    }

    inner class InnerClass {
        fun foo() = "InnerClass foo $info"
    }
}

fun main() {
    val outer = Outer();

    val b = outer.InnerClass();
    println(b.foo());

    println(Outer.NestedClass().foo())
}