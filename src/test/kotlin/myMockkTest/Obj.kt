package myMockkTest

data class Obj(val value: Int) {}

class Ext {
    fun Obj.extensionMyFunc() = value + 5;
}
