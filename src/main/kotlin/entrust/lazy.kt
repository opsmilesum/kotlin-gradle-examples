package entrust

val lazyValue: String by lazy {
    println("Computing...")
    "PuJing"
    "BaiDeng"
}

fun main() {
    println(lazyValue)
    println(lazyValue)
}

