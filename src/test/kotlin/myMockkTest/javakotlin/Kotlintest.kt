package myMockkTest.javakotlin

import myMockk.Customer
import org.junit.jupiter.api.Test
import java.util.Calendar

class Kotlintest {

    fun calendarDemo() {
        val calendar = Calendar.getInstance()
        if (calendar.firstDayOfWeek == Calendar.SUNDAY) { // call getFirstDayOfWeek()
            calendar.firstDayOfWeek = Calendar.WEDNESDAY // call setFirstDayOfWeek()
        }
       println("Day is ${calendar.firstDayOfWeek}")
    }

    @Test
    fun test() {
      val cc= CustomerKt()
      val customer = Customer("Phase")
      customer.name
      println(customer.name)
      println(customer.placeOrder())
    }
}