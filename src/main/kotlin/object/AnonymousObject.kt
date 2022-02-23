package `object`

abstract class MouseAdapter {
    fun onClick() = println("Click from MouseAdapter")

    open fun onEntered() {
        println("Enter from MouseAdapter")
    }

    open fun onReturn() = println("Return from MouseAdapter")
}

fun main() {
    val obj = object : MouseAdapter() {
        override fun onEntered() {
            println("Enter from new MouseAdapter")
        }

        override fun onReturn() = println("Return from New MouseAdapter");
    }

    obj.onClick()
    obj.onEntered()
    obj.onReturn()
}

/**
Click from new MouseAdapter
Enter from new MouseAdapter
 * */