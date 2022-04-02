package myMockkTest

enum class DoorType {
    FRONT_LEFT,
    FRONT_RIGHT,
}

class Window {
    fun windowState(): String = "Unclear"
}

class Car {
    fun drive(direction: Direction = Direction.NORTH) = accelerate() + direction.name

    fun recordTelemetry(speed: Int = 0, direction: Direction = Direction.NORTH, long: Double = 1.0): String {
        return ""
    }

    private fun accelerate() = "go to "

    fun door(doorType: DoorType): Window {
        return Window();
    }

    fun run(): Unit {
        println("Run")
    }
}