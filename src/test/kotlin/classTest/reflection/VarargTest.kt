package classTest.reflection

import org.junit.jupiter.api.Test

fun foo(boolean: Boolean, vararg args: String) {
    for(i in args.indices) {
        println(args[i])
    }
}

class VarargTest {

    @Test
    fun test() {
        foo(true, "1", "2", "3")
    }
}