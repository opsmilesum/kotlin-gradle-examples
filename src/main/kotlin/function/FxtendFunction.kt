package function

fun String.extendFunction(): String = this.substring(1, this.length - 1)

fun main() {
    val str = "XHello, WorldX"
    println(str.extendFunction())
}