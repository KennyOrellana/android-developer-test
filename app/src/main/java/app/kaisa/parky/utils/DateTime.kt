package app.kaisa.parky.utils

import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.repository.CarTypeSingleton
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

object DateTime {
    fun formatMinutes(time: Int) : String {
        val hours: Int = time / 60
        val minutes: Int = time % 60
        return when {
            hours == 0 && (minutes == 0 ||  minutes == 1) -> "1 m"
            hours == 0 -> "$minutes m"
            else -> String.format("%dh %02dm", hours, minutes)
        }
    }

    fun doubleToMoney(amount: Double) : String {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount)
    }

    fun millisToMinutes(millis: Long) : Int {
        return (millis / 60000.0).toInt()
    }

    fun millisToDate(carRecord: CarRecord) : String {
        carRecord.record?.dateInput?.let {
            if(it > 0) {
                return DateFormat.getDateInstance(DateFormat.SHORT).format(Date(it))
            }
        }

        return ""
    }

    fun elapsedTime(carRecord: CarRecord) : String {
        carRecord.record?.dateInput?.let {
            if(it > 0) {
                return formatMinutes(millisToMinutes(System.currentTimeMillis() - it))
            }
        }
        
        return ""
    }

    fun calculateAmount(carRecord: CarRecord) : String {
        carRecord.record?.dateInput?.let { date ->
            val cost = CarTypeSingleton.getPrice(carRecord.car.type)
            if(date > 0 && cost!=null) {
                return doubleToMoney(millisToMinutes(System.currentTimeMillis() - date) * cost)
            }
        }

        return ""
    }
}