package function

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ScopeFunctionTest {
    val arrivalEstimated = LocalDateTime.of(2021, 2, 4, 17, 0, 0, 10)

    // Use it
    // Returns lambda result
    @Test
    fun test_let() {
        val str: String? = "Hello"
        val length = str?.let {
            it.length
        }
        length shouldBe 5
    }

    // Use this
    // Returns lambda result
    @Test
    fun test_with() {
        val numbers = mutableListOf("One", "Two", "Three")
        val first = with(numbers) {
            "First elements: " + first()
        }
        first shouldBe "First elements: One"
    }

    // Use this
    // Returns lambda result
    @Test
    fun test_run() {

    }

    data class Person(var name: String, var age: Int) {}
    // Use this
    // Returns Object it self
    @Test
    fun test_apply() {
        val pony = Person("robin", 30).apply { age = 40 }
        pony.age shouldBe 40
    }


    // It
    // Returns Object it self
    @Test
    fun test_also() {
        val pony = Person("robin", 30).also { it.age = 40 }
        pony.age shouldBe 40
    }

    @Test
    fun takeIf() {

        "1100".indexOf("00").takeIf { it > 0 }?.let {
            "Find in index $it"
        } shouldBe "Find in index 2"

        "1100".indexOf("12").takeIf { it > 0 }?.let {
            "Find in index $it"
        } shouldBe null
    }
}