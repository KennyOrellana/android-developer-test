package app.kaisa.parky.data.repository

import app.kaisa.parky.data.models.CarType

object CarTypeSingleton {
    val carTypes = ArrayList<CarType>() //Saved as static to compute amounts globally

    fun getPrice(id: Int) : Double? {
        return carTypes.firstOrNull { it.idType == id }?.price
    }
}