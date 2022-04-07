## runBlocking
1. Runs a new coroutine and blocks the current thread until its completion.
2. Used in `main` and `test`.
```kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
```

## launch
Launches a new `coroutine` without blocking the current `thread and returns a reference to the coroutine as a `Job`.

## delay
Delays coroutine for a given time without blocking a thread and resumes it after a specified time.

## coroutineScope
creates a coroutine scope and does not complete until all launched children complete.
```kotlin
fun main() = runBlocking {
    doWorld()
}

suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}
```

## Job
```kotlin
val job = launch { // launch a new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
}
println("Hello")
job.join() // wait until child coroutine completes
println("Done")
```
