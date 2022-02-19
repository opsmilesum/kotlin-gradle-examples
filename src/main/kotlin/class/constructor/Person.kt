package `class`.constructor

class Person(_name: String, _age: Int) {
    val name: String;
    var age: Int = _age;

    init {
        name = _name.capitalize();

        print("Name: $name\n");
        print("Age: $age")
    }
}

fun main() {
    var person = Person("pony", 12);
}