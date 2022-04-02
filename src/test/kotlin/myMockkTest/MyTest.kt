package myMockkTest

import io.mockk.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.PrintStream
import kotlin.test.assertEquals

class MyTest {

    @Test
    fun spy() {
        val car = spyk<Car>()
        assertEquals("go to NORTH", car.drive(Direction.NORTH))
        verify { car.drive(Direction.NORTH) }
    }

    @Test
    fun relaxed_mock() {
        val car = mockk<Car>(relaxed = true)
        assertEquals("", car.drive(Direction.NORTH))
        verify { car.drive(Direction.NORTH) }
    }

    @Test
    fun partial_mock() {
        val adder = mockk<Adder>()
        every { adder.addOne(any()) } returns -1;
        every { adder.addOne(3) } answers { callOriginal() }

        assertEquals(-1, adder.addOne(2))
        assertEquals(4, adder.addOne(3))
    }

    @Test
    fun object_mock() {
        mockkObject(MyObject)
        assertEquals(3, MyObject.add(1, 2))
        every { MyObject.add(1, 2) } returns 5
        assertEquals(5, MyObject.add(1, 2))
    }

    @Test
    fun class_mock() {
        val car = mockkClass(Car::class)
        every { car.drive(Direction.NORTH) } returns "OK"
        assertEquals("OK", car.drive(Direction.NORTH))
        verify { car.drive(Direction.NORTH) }
    }

    @Test
    fun enum_mock() {
        mockkObject(Enumeration.ENUM_A)
        every { Enumeration.ENUM_A.goodInt } returns 33
        assertEquals(33, Enumeration.ENUM_A.goodInt)
    }

    @Test
    fun mock_constructor() {
        mockkConstructor(Adder::class)
        every { anyConstructed<Adder>().addOne(3) } returns 10
        assertEquals(10, Adder().addOne(3))
        verify { anyConstructed<Adder>().addOne(3) }

        // every { anyConstructed<Adder>(OfTypeMatcher<String>(String::class)).add(1) } returns 2
    }

    @Test
    fun partial_argument_matching() {
        val car = mockk<Car>(relaxed = true)
        every {
            car.recordTelemetry(
                speed = more(50),
                direction = Direction.NORTH,
                long = any()
            )
        } returns "OK"
        assertEquals("", car.recordTelemetry(30, Direction.NORTH, 1.0))
        assertEquals("OK", car.recordTelemetry(60, Direction.NORTH, 1.0))
    }

    @Test
    fun chained_calls() {
        val car = spyk<Car>() {
            every { door(DoorType.FRONT_LEFT).windowState() } returns "Up"
        }
        assertEquals("Unclear", car.door(DoorType.FRONT_RIGHT).windowState())
        assertEquals("Up", car.door(DoorType.FRONT_LEFT).windowState())
    }

    @Test
    fun hierarchical() {
        val addressBook = mockk<AddressBook> {
            every { contacts } returns listOf(
                mockk {
                    every { name } returns "pony"
                    every { address } returns mockk {
                        every { city } returns "Shanghai"
                    }
                }
            )
        }
        assertEquals("pony", addressBook.contacts[0].name)
        assertEquals("Shanghai", addressBook.contacts[0].address.city)
    }

    @Test
    fun capturing_arguments() {
        val slotSpeed = slot<Int>()
        val car = mockk<Car>()
        every {
            car.recordTelemetry(
                speed = capture(slotSpeed),
                direction = Direction.NORTH,
                long = any()
            )
        } answers {
            println("OK")
            "OK"
        }
        assertEquals("OK", car.recordTelemetry(30, Direction.NORTH, 1.0))
        assertEquals(30, slotSpeed.captured)
    }

    @Test
    fun verification_times() {
        val car = mockk<Car>(relaxed = true)

        car.recordTelemetry(20)
        car.recordTelemetry(30)
        car.recordTelemetry(40)

        verify(atLeast = 2) { car.recordTelemetry(speed = or(20, 30)) }
        verify(atMost = 3) { car.recordTelemetry(speed = more(20, andEquals = true)) }
    }

    @Test
    fun verification_order() {
        val car = mockk<Car>(relaxed = true)
        car.recordTelemetry(20)
        car.recordTelemetry(30)
        car.recordTelemetry(40)

        verifyOrder {
            car.recordTelemetry(20)
            car.recordTelemetry(40)
        }
    }

    @Test
    fun verification_timeout() {
        mockk<Car> {
            every { recordTelemetry() } returns "OK"

            Thread {
                Thread.sleep(2000)
                recordTelemetry()
            }.start()

            verify(timeout = 3000) { recordTelemetry() }
        }
    }

    @Test
    fun return_unit() {
        mockk<Car> {
            justRun { run() }
        }
    }

    @Test
    fun extension_function_object() {
        with(mockk<Ext>()) {
            every {
                Obj(5).extensionMyFunc()
            } returns 11

            assertEquals(11, Obj(5).extensionMyFunc())

            verify { Obj(5).extensionMyFunc() }
        }
    }

    interface ClsWithManyArgs {
        fun manyMany(vararg x: Any): Int = 0
    }

    @Test
    fun varargs_mock() {
        val obj = mockk<ClsWithManyArgs>(relaxed = true)
        every { obj.manyMany(5, 6, *varargAll { it == 7 }) } returns 3
        assertEquals(3, obj.manyMany(5, 6, 7))
        assertEquals(3, obj.manyMany(5, 6, 7, 7))
        assertEquals(0, obj.manyMany(5, 6, 8))

        every { obj.manyMany(5, 6, *anyVararg(), 7) } returns 4
        assertEquals(4, obj.manyMany(5, 6, 1, 7))
        assertEquals(4, obj.manyMany(5, 6, 2, 3, 7))
        assertEquals(0, obj.manyMany(5, 6, 2, 3, 8))
    }

    @Test
    fun mock_private_function() {
        val mock = spyk<Car>(recordPrivateCalls = true)
        every { mock["accelerate"]() } returns "mock accelerate "
        assertEquals("mock accelerate NORTH", mock.drive())
        verifySequence {
            mock.drive(Direction.NORTH)
            mock["accelerate"]()
        }
    }

    @Disabled
    @Test
    fun multiple_interface() {
        val spy = spyk(System.out, moreInterfaces = *arrayOf(Runnable::class))
        spy.println(555)

        every {
            (spy as Runnable).run()
        } answers {
            (self as PrintStream).println("Run! Run! Run!")
        }

        val thread = Thread(spy as Runnable)
        thread.start()
        thread.join()
    }
}