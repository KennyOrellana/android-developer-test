package app.kaisa.parky.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.repository.ParkyRepository

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ParkyRepository = ParkyRepository(application)

    fun getCars() = repository.getCars()

    fun addCar(car: Car) {
        repository.addCar(car)
    }
}