package function

// Function can be variable.
fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, element: T) -> R): R {
    var accumulator: R = initial;
    for (element: T in this) {
        accumulator = combine(accumulator, element);
    }
    return accumulator
}


fun main() {
    val item = listOf(1, 2, 3, 4, 5)
    val result = item.fold(0, { acc: Int, i: Int ->
        println("Accumulator: $acc, Element: $i")
        val result = acc + i;
        result;
    })
    println(result) // 15
}