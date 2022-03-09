package function

fun stringConcat(compute: (str1: String, str2: String) -> String): String = compute.invoke("Pony", " Ma");

fun main() {
    println(stringConcat { s1: String, s2: String -> s1 + s2 })
    println(stringConcat { s1: String, s2: String -> "$s1 not$s2" })

    // lambda
    val stringPlusV2: (String, String) -> String = { str1: String, str2: String -> str1 + str2 }
    println(stringPlusV2.invoke("Hello", ",World"))

    val stringPlus: (String, String) -> String = String::plus
    println(stringPlus.invoke("Hello", ",World"))
    println(stringPlus("Hello", ",World"))
}