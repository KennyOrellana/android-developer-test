package app.kaisa.parky.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import app.kaisa.parky.data.dao.CarDao
import app.kaisa.parky.data.dao.CarTypeDao
import app.kaisa.parky.data.dao.RecordDao
import app.kaisa.parky.data.db.ParkyDatabase
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.models.Record
import app.kaisa.parky.utils.DateTime
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

    //Cars
    fun updateCars(car: Car) = carDao?.updateCar(car)

    fun deleteCar(car: Car){
        launch {
            withContext(Dispatchers.IO) {
                carDao?.deleteCar(car)
            }
        }
    }

    fun getCars() = carDao?.getCars()
    fun getCarsWithInputs() = carDao?.getCarsWithInputs()
    fun getCarsWithoutInputs() = carDao?.getCarsWithoutInputs()

    //Search
    fun searchCars(plate: String? = null, filters: List<CarType>? = null) : LiveData<List<Car>>? {
        return when {
            plate?.isNotEmpty() == true && filters != null -> {
                carDao?.searchCar(plate, filters.map { it.idType })
            }

            plate?.isNotEmpty() == true -> {
                return carDao?.searchCar(plate)
            }

            filters != null -> {
                carDao?.searchCar(filters.map { it.idType })
            }

            else -> null
        }
    }

    fun searchCarsWithInputs(plate: String? = null, filters: List<CarType>? = null) : LiveData<List<CarRecord>>? {
        return when {
            plate?.isNotEmpty() == true && filters != null -> {
                carDao?.searchCarsWithInputs(plate, filters.map { it.idType })
            }

            plate?.isNotEmpty() == true -> {
                return carDao?.searchCarsWithInputs(plate)
            }

            filters != null -> {
                carDao?.searchCarsWithInputs(filters.map { it.idType })
            }

            else -> null
        }
    }

    fun searchCarsWithoutInputs(plate: String? = null, filters: List<CarType>? = null) : LiveData<List<CarRecord>>? {
        return when {
            plate?.isNotEmpty() == true && filters != null -> {
                carDao?.searchCarsWithoutInputs(plate, filters.map { it.idType })
            }

            plate?.isNotEmpty() == true -> {
                return carDao?.searchCarsWithoutInputs(plate)
            }

            filters != null -> {
                carDao?.searchCarsWithoutInputs(filters.map { it.idType })
            }

            else -> null
        }
    }

    fun addCar(car: Car){
        launch { addCarBG(car) }
    }

    //TODO understand why is implemented in this way
    private suspend fun addCarBG(car: Car){
        withContext(Dispatchers.IO){
            carDao?.insertCar(car)
        }
    }

    //Car Types
    fun getCarTypes() = carTypeDao?.getCarTypes()
    fun insertCarTypes(cardTypes: List<CarType>) = launch { addCarTypesBG(cardTypes) }

    private suspend fun addCarTypesBG(cardTypes: List<CarType>){
        withContext(Dispatchers.IO){
            carTypeDao?.insertCarTypes(cardTypes)
        }
    }

    //Records
    fun insertRecord(carId: String) {
        launch {
            withContext(Dispatchers.IO) {
                recordDao?.insertRecord(Record(carId))
            }
        }
    }

    fun updateRecord(record: Record) {
        recordDao?.updateRecord(record)
    }

    //Checkout Car/Record
    fun checkoutCar(carRecord: CarRecord){
        launch {
            val record = carRecord.record
            record?.dateOutput = System.currentTimeMillis()

            val car = carRecord.car
            car.addMinutes(DateTime.elapsedMinutes(record))

            withContext(Dispatchers.IO) {
                updateCars(car)
                if (record != null) {
                    updateRecord(record)
                }
            }
        }
    }
}