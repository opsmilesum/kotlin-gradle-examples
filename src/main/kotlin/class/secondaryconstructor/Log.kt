package `class`.secondaryconstructor

open class Log {
    var data: String = "";
    var userId: Int;

    constructor(_data: String, _userId: Int) {
        data = _data;
        userId = _userId;
        print("Data: $data User Id: $userId");
    }
}

class AuthLog : Log {
    constructor(_data: String) : this("From AuthLog ->" + _data, 10086) {

    }

    constructor(_data: String, _userId: Int) : super(_data, _userId) {

    }
}

fun main() {
    val authLog = AuthLog("Success!");
    print("\nUser Id is: ${authLog.userId}")
}