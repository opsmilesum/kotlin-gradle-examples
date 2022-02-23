package entrust

import kotlin.reflect.KProperty

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "The entrusted property: ${property.name}"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("Set Value: `$value` to property: ${property.name}")
    }
}

fun main() {
    val e = Example();
    println(e.p)

    e.p = "New P"
    println(e.p)
}