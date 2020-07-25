package app.kaisa.parky.utils

import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.models.Record
import app.kaisa.parky.data.repository.CarTypeSingleton
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    private const val DATE_FORMAT = "dd/MM/yy hh:mm a"

    fun formatMinutes(time: Int) : String {
        val hours: Int = time / 60
        val minutes: Int = time % 60
        return when {
            hours == 0 && minutes == 0 -> ""
            hours == 0 && minutes == 1 -> "1 minuto"
            hours == 0 -> "$minutes minutos"
            hours == 1 -> String.format("%d hora %02d minutos", hours, minutes)
            else -> String.format("%d horas %02d minutos", hours, minutes)
        }
    }

    private fun doubleToMoney(amount: Double) : String {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount)
    }

    private fun millisToMinutes(millis: Long) : Int {
        return (millis / 60_000.0).toInt()
    }

    fun millisToDate(carRecord: CarRecord) : String {
        carRecord.record?.dateInput?.let {
            if(it > 0) {
                return SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date(it))
            }
        }

        return ""
    }

    fun elapsedMinutes(record: Record?) : Int {
        record?.dateInput?.let {
            if(it > 0) {
                return millisToMinutes(System.currentTimeMillis() - it)
            }
        }

        return 0
    }

    fun elapsedTime(carRecord: CarRecord) : String {
        return formatMinutes(elapsedMinutes(carRecord.record))
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

    fun currentDebt(carRecord: CarRecord) : String {
       return currentDebt(carRecord.car)
    }

    fun currentDebt(car: Car) : String {
        val cost = CarTypeSingleton.getPrice(car.type)
        return if(cost!=null) {
            doubleToMoney(car.minutes * cost)
        } else {
            doubleToMoney(0.0)
        }
    }
}