package `class`.inherit

open class Log {
    var data: String = ""

    constructor(_data: String) {
        data = _data.toUpperCase();
        print("data: $data\n");
    }

    open fun display() {
        print("Display from Log.\n")
    }
}

class AuthLog : Log {
    var autoInformation: String = "";

    constructor(_data: String, _autoInformation: String) : super(_data) {
        print("autoInformation $autoInformation\n")
    }

    override fun display() {
        print("Display from AuthLog.\n")
        print("The data from super is ${super.data}")
    }
}

fun main() {
    var authLog = AuthLog("data1", "autoInformation1");
    authLog.display();
}