package app.kaisa.parky.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.repository.ParkyRepository

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ParkyRepository = ParkyRepository(application)

    //Cars
    fun getCars() = repository.getCars()
    fun getCarsWithInputs() = repository.getCarsWithInputs()
    fun getCarsWithoutInputs() = repository.getCarsWithoutInputs()

    //Search
    fun searchCars(plate: String? = null, filters: List<CarType>? = null) = repository.searchCars(plate, filters)
    fun searchCarsWithInputs(plate: String) = repository.searchCarsWithInputs(plate)
    fun searchCarsWithoutInputs(plate: String) = repository.searchCarsWithoutInputs(plate)

    fun addCar(car: Car) { repository.addCar(car) }
    fun addCars(cars: List<Car>) { repository.addCars(cars) }
    fun deleteCar(car: Car) { repository.deleteCar(car) }

    //Car Type
    fun getCarTypes() = repository.getCarTypes()
    fun insertCarTypes(cardTypes: List<CarType>) = repository.insertCarTypes(cardTypes)

    //Records
    fun insertRecord(car: Car) = repository.insertRecord(car.idCar)
    fun deleteRecordsOfficial() = repository.deleteRecordsOfficial()

    //Checkout
    fun checkoutCar(carRecord: CarRecord) = repository.checkoutCar(carRecord)
}