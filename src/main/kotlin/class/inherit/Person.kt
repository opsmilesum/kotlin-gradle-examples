package `class`.inherit

open class Person(name: String, age: Int) {
    init {
        print("My name is $name\n")
        print("My age is $age\n")
    }
}

class Teacher(name: String, age: Int) : Person(name, age) {
    init {
        print("I'm a teacher\n");
    }
}

fun main() {
    val teacher = Teacher("HuHongBin", 32);
}

