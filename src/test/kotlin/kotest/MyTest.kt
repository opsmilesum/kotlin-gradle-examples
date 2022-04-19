package kotest

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.inspectors.forAtLeast
import io.kotest.inspectors.forAtMost
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualToComparingFieldsExcept
import io.kotest.matchers.equality.shouldNotBeEqualToComparingFields
import io.kotest.matchers.equality.shouldNotBeEqualToComparingFieldsExcept
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldHaveMaxLength
import io.kotest.matchers.string.shouldHaveMinLength
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException


class MyTest {

    @Test
    fun shouldBe_shouldNotBe() {
        val name = "sam";
        name shouldBe "sam"

        name shouldNotBe "jack"
    }

    @Test
    fun shouldContain() {
        val name = "anyOne"
        name shouldContain "One" shouldStartWith "any"
        name.shouldContain("One").shouldStartWith("any")
    }

    @Test
    fun inspectors_test() {
        val xs = listOf("sam", "gareth", "timothy", "muhammad")
        xs.forAtLeast(2) {
            it.shouldHaveMinLength(7)
        }

        xs.forAtMost(1) {
            it.shouldHaveMaxLength(3)
        }
    }

    class Foo(_id: Int, _description: String = "", _secret: String = "") {
        val id: Int = _id;
        val description: String = _description;

        private val secret: String = _secret;
    }

    @Test
    fun shouldBeEqualToComparingFields() {
        val foo1 = Foo(1, "Foo1")
        val foo2 = Foo(1, "Foo1")
        foo1.shouldBeEqualToComparingFields(foo2)

        val foo3 = Foo(1, "", _secret = "A")
        val foo4 = Foo(1, "", _secret = "B")
        foo1.shouldBeEqualToComparingFields(foo2)
    }

    @Test
    fun shouldBeEqualToComparingFields_ignorePrivateFields() {
        val foo1 = Foo(1, _secret = "A")
        val foo2 = Foo(1, _secret = "B")
        foo1.shouldNotBeEqualToComparingFields(foo2, false)
        foo1.shouldBeEqualToComparingFields(foo2, true)
    }

    @Test
    fun shouldBeEqualToComparingFieldsExcept() {
        val foo1 = Foo(1, "Foo1")
        val foo2 = Foo(1, "Foo2")
        foo1.shouldBeEqualToComparingFieldsExcept(foo2, Foo::description)
    }

    @Test
    fun shouldBeEqualToComparingFieldsExcept_ignorePrivate() {
        val foo1 = Foo(1, "Foo1", "A")
        val foo2 = Foo(1, "Foo2", "B")
        foo1.shouldNotBeEqualToComparingFieldsExcept(foo2, false, Foo::description)
        foo1.shouldBeEqualToComparingFieldsExcept(foo2, true, Foo::description)
    }

    @Test
    fun shouldThrow() {
        val exception = shouldThrow<FileNotFoundException> {
            throw FileNotFoundException("Something went wrong")
        }
        exception.message.shouldStartWith("Something went wrong")
    }

    class Person(private val fieldA: String, private val fieldB: String)

    @Test
    fun test () {
        val  person1 = Person("valueA", "valueB")
        val  person2 = Person("valueA", "XXX")
//        person1.shouldBeEqualToComparingFieldsExcept(person2, Person::fieldB);
    }
}