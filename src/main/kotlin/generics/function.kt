package generics

fun <T> doSupperPrintln(value: T) {
    when (value) {
        is Int -> println("Integer $value")
        is String -> println("String $value")
    }
}

fun main() {
    doSupperPrintln(3)
    doSupperPrintln("Three")
}