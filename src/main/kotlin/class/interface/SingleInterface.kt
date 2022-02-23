package `class`.`interface`

interface Person {
    val age: Int;
    fun foo(): String
    fun hello() {
        println("Hello guys")
    }
}

class Student : Person {
    override val age: Int = 25;
    override fun foo() = "It's foo implementation"
}

fun main() {
    val student = Student();
    println("Student age is ${student.age}")
    student.hello();
    println(student.foo())
}