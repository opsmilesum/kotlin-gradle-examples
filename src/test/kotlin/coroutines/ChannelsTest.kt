package coroutines

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ChannelsTest {

    @Test
    fun basic_test() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for(x in 1..3) channel.send(x * x)
            channel.close()
        }

        var i = 0
        for(y in channel) {
            i++;
            y shouldBe i * i
        }
    }

    fun CoroutineScope.produceNums(): ReceiveChannel<Int> = produce {
        for(x in 1..3) send(x * x)
    }

    @Test
    fun producer_consumer() = runBlocking {
        val nums = produceNums()
        var total = 0;
        produceNums().consumeEach {  total += it}
        total shouldBe 14
    }

    @Test
    fun buffered_channels() {
        val channel = Channel<Int>(4)
    }
}