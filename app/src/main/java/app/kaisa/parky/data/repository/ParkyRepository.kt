package app.kaisa.parky.data.repository

import android.app.Application
import app.kaisa.parky.data.dao.CarDao
import app.kaisa.parky.data.dao.CarTypeDao
import app.kaisa.parky.data.dao.RecordDao
import app.kaisa.parky.data.db.ParkyDatabase
import app.kaisa.parky.data.models.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ParkyRepository (application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var carDao: CarDao?
    private var carTypeDao: CarTypeDao?
    private var recordDao: RecordDao?

    init {
        val db = ParkyDatabase.getDB(application)
        carDao = db.carDao()
        carTypeDao = db.carTypeDao()
        recordDao = db.recordDao()
    }

    fun getCars() = carDao?.getCars()

    fun searchCars(plate: String) = carDao?.searchCar(plate)

    fun addCar(car: Car){
        launch { addCarBG(car) }
    }

    //TODO understand why is implemented in this way
    private suspend fun addCarBG(car: Car){
        withContext(Dispatchers.IO){
            carDao?.insertCar(car)
        }
    }
}