package classTest.reflection

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class PropertyReferenceTest {
    class Goods {
        var name = "apple"
        private val price = 7.5

        fun getPrice(): Double {
            return price
        }
    }

    @Test
    fun property_reference() {
        val good = Goods()
        val props = Goods::class.declaredMemberProperties
        val nameProperty = props.filter { it.name == "name" }[0] as KProperty<*>
        val priceProperty = props.filter { it.name == "price" }[0]

        nameProperty.getter.call(good) shouldBe "apple"

        val nameProperty2 = Goods::name
        nameProperty2.set(good, "orange")
        good.name shouldBe "orange"
    }
}
