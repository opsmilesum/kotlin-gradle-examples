package `class`

import org.jetbrains.annotations.TestOnly

class NullSafety {
    @TestOnly
    fun test() {
        var b: String? = "abc"
        // b = null

        val l = if(b != null) b.length else -1

        println(b?.length)

        val l2 = b?.length ?: -1
        val l3 = b?.length ?: throw IllegalArgumentException("")
        val l4 = b!!.length
        val l5 = b as? Int

        val nullableList = listOf(1, 2, null, 4)
        val intList = nullableList.filterNotNull()
    }
}