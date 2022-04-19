package coroutines

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

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

    @Test
    fun coroutine_context(): Unit = runBlocking {
        launch {
            println("Running on ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("MyThread")) {
            println("Running on ${Thread.currentThread().name}")
        }
    }

    @Test
    fun exception_handling() = runBlocking {
        val job = GlobalScope.launch {
            throw java.lang.IndexOutOfBoundsException()
        }
        // Cancel job
        // job.cancel().
        job.join()

        val deferred = GlobalScope.async {
            throw java.lang.ArithmeticException()
        }

        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println(e)
        }
    }

    @Test
    fun exception_handler() = runBlocking {
        val handler = CoroutineExceptionHandler {_, exception ->
            println("Catch exception ${exception.message}")
        }
        val job = GlobalScope.launch(handler) {
            throw AssertionError()
        }
        job.join()
    }

    @Test
    fun supervisor() = runBlocking {
        // the cancellation is propagated only downwards.
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChildren = launch(CoroutineExceptionHandler {_, _ ->} ) {
                println("The first child is failing")
                throw AssertionError("The first child is cancelled")
            }

            val secondChild = launch {
                firstChildren.join()
                println("The first Children is cancelled: ${firstChildren.isCancelled}, but the second is active.")
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    println("The second children is cancelled")
                }
            }

            firstChildren.join()
            println("Cancelling supervisor")
            supervisor.cancel()
            secondChild.join()
        }
    }

    suspend fun massiveRun(action: suspend () -> Unit) {
        val n = 100  // number of coroutines to launch
        val k = 1000 // times an action is repeated by each coroutine
        val time = measureTimeMillis {
            coroutineScope { // scope for coroutines
                repeat(n) {
                    launch {
                        repeat(k) { action() }
                    }
                }
            }
        }
        println("Completed ${n * k} actions in $time ms")
    }

    val mutex = Mutex()
    var counter = 0

    @Test
    fun mutual_exclusion() = runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock {
                    counter++
                }
            }
        }
        counter shouldBe 100000
    }
}