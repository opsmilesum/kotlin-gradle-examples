package entrust

fun example(compute: () -> Int, match: Boolean) {
    val memoizedInt by lazy(compute)
    if (match) {
        println(memoizedInt);
    }
}

fun main() {

}