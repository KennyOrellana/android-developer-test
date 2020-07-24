package app.kaisa.parky.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCars(cars: List<Car>)

    @Delete
    fun deleteCar(car: Car)

    @Update
    fun updateCar(car: Car)

    @Query("SELECT * FROM car ORDER BY idCar ASC")
    fun getCars() : LiveData<List<Car>>

//    @Query("SELECT car.id, car.minutes, car.type, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.id = record.car ORDER BY car.id ASC")
    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.idCar = record.carId ORDER BY car.idCar ASC")
    fun getCarsWithInputs() : LiveData<List<CarRecord>>

//    @Query("SELECT car.idCar, car.minutes, car.type, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.car WHERE record.car IS NULL ORDER BY car.idCar ASC")
    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.carId WHERE record.carId IS NULL ORDER BY car.idCar ASC")
    fun getCarsWithoutInputs() : LiveData<List<CarRecord>>

    @Query("SELECT * FROM car WHERE idCar LIKE :plate || '%' ORDER BY idCar ASC")
    fun searchCar(plate: String) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE type IN (:type) ORDER BY idCar ASC")
    fun searchCar(type: List<Int>) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE idCar LIKE :plate || '%' AND type IN (:type) ORDER BY idCar ASC")
    fun searchCar(plate: String, type: List<Int>) : LiveData<List<Car>>
}