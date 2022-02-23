package entrust

import kotlin.properties.Delegates

class Observable {
    var key: Int by Delegates.observable(0) { prop, old, new ->
        println("Property: ${prop.name}, old value: $old, new value: $new.")
    }
}

fun main() {
    val observable = Observable();
    observable.key = 10086
    observable.key = 12306
}