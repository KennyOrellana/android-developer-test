package app.kaisa.parky.utils

import app.kaisa.parky.data.models.CarRecord

interface CarListener {
    fun onClick(carRecord: CarRecord)
}