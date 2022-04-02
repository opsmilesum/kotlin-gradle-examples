package myMockkTest.pkg

data class Obj(val value: Int)

// declared in File.kt ("pkg" package)
fun Obj.extensionFunc() = value + 5