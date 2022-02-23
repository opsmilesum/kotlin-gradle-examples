package `class`.abstract

abstract class Person(name: String) {
    var age: Int = 40;
    abstract var job: String;

    init {
        println("My name is $name, age is $age.");
    }

    abstract fun displayJob();
}

class Teacher(name: String) : Person(name) {
    override var job: String = "Teacher";

    override fun displayJob() {
        println("My job is $job.");
    }
}

fun main() {
    val teacher = Teacher("PuJing");
    teacher.displayJob();
}

