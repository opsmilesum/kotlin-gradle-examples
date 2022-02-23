package `object`

object SingletonObject {
    private var value: Int = 10;
    var publicValue: Int = -1;

    fun getPrivateValue(): Int {
        value = 12;
        return value;
    }
}

fun main() {
    val privateValue = SingletonObject.getPrivateValue();
    println("Object public value: ${SingletonObject.publicValue}, private value: ${SingletonObject.getPrivateValue()}")
}