package app.kaisa.parky.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.kaisa.parky.data.models.Car

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

    @Query("SELECT * FROM car ORDER BY id ASC")
    fun getCars() : LiveData<List<Car>>

    @Query("SELECT car.id, car.minutes, car.type, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.id = record.car ORDER BY car.id ASC")
    fun getCarsWithInputs() : LiveData<List<Car>>

    @Query("SELECT car.id, car.minutes, car.type, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.id = record.car WHERE record.car IS NULL ORDER BY car.id ASC")
    fun getCarsWithoutInputs() : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE id LIKE :plate || '%' ORDER BY id ASC")
    fun searchCar(plate: String) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE type IN (:type) ORDER BY id ASC")
    fun searchCar(type: List<Int>) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE id LIKE :plate || '%' AND type IN (:type) ORDER BY id ASC")
    fun searchCar(plate: String, type: List<Int>) : LiveData<List<Car>>
}