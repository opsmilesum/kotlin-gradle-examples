package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CoroutineTest {

    @Test
    fun first() {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello, ")
        Thread.sleep(2000L)
    }

    @Test
    fun runBlocking() {
        GlobalScope.launch {
            delay(1000L)
            println("World")
        }
        println("Hello, ")
        runBlocking {
            delay(2000L)
        }
    }

    @Test
    suspend fun join() {
        val job = GlobalScope.launch {
            delay(1000)
            print("OK")
        }
        print("NOT")
        job.join()
    }

    // RunBlocking: Block current thread.
    // Launch: start a new coroutine without blocking current thread,
    @Test
    fun test() = runBlocking {
        launch {
            delay(1000)
            println("OK")
        }
        println("Hello, ")
    }
}