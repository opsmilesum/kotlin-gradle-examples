package function

class Student() {
    infix fun printId(id: Int) {
        print("Student id is: $id\n")
    }

    fun info(sex: Char = 'M', age: Int) {
        print("Student age is: $age, sex is: $sex\n")
    }
}

fun main() {
    val student = Student()
    
    student printId 12306
    student printId 10086

    student.info('W', 10);
    student.info(age = 20);
}