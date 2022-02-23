package `class`.dataclass

data class User(val name: String, val age: Int)

fun main() {
    val user1 = User("PuJing", 25)
    val user2 = user1.copy();
    val user3 = user1.copy(name = "BaiDeng")

    println(user1.toString())
    println(user2.toString())
    println(user3.toString())

    println(user1.equals(user2))
    println(user1.equals(user3))

    val (name, age) = user3;
    print("User3 name: $name, age: $age")
}