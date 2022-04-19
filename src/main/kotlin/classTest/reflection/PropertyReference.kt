package classTest.reflection

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

class Person(val name: String, var age: Int, private var address: String = "") {
    fun present() = "I'm $name, and I'm $age years old"
    fun greet(other: String) = "Hi, $other, I'm $name"

    //private val address: String = "";
}

fun <T> printProperty(instance: T, prop: KProperty1<T, *>) {
    println("prop : ${prop.get(instance)}")
}

fun <T> incrementProperty(instance: T, prop: KMutableProperty1<T, Int>) {
    val value = prop.get(instance)
    prop.set(instance, value + 1)
}
fun main() {

    val person = Person("robin", 12)
    printProperty(person, Person::name)
    printProperty(person, Person::age)
    // printProperty(person, Person::address)
    // incrementProperty(person, Person::age)
}
