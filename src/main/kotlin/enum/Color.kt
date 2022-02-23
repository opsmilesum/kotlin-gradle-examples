package enum

enum class Color {
    RED {
        override fun signal() {
            println("The is RED")
        }
    },
    GREEN {
        override fun signal() {
            println("This is GREEN")
        }
    };

    abstract fun signal()
}

fun main() {
    println(Color.values().size)
    println(Color.valueOf("RED"))
    var color = Color.GREEN;
    println(color.name)
    println(color.ordinal)
}

