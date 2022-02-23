package `object`

class CompanionTest {

    companion object {
        fun callMe() {
            println("Call me")
        }
    }
}

fun main() {
    CompanionTest.callMe();
}