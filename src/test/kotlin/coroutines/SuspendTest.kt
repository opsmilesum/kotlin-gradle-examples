package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class SuspendTest {
    suspend fun doA(): Int {
        println("Do A")
        delay(1000)
        return 1
    }

    suspend fun doB(): Int {
        println("Do B")
        delay(1000)
        return 2
    }

    @Test
    fun test1() = runBlocking {
        val time = measureTimeMillis {
            val doA = doA()
            println("Point2")
            val doB = doB()
            println("Point 1")
            println("Result ${doA + doB}")
        }
        println("Time consumption: $time ms")
    }

    @Test
    fun test2() = runBlocking {
        val time = measureTimeMillis {
            val a = async { doA() }
            val b = async { doB() }
            println("Point 1")
            println("Result:${a.await() + b.await()}")
        }
        println("Time consumption: $time")
    }
}