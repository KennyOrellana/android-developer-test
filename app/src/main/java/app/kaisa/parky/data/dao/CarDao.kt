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

    //Get
    @Query("SELECT * FROM car ORDER BY idCar ASC")
    fun getCars() : LiveData<List<Car>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.idCar = record.carId WHERE record.dateOutput IS NULL ORDER BY car.idCar ASC")
    fun getCarsWithInputs() : LiveData<List<CarRecord>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.carId WHERE (record.carId IS NULL OR record.dateOutput IS NOT NULL) ORDER BY car.idCar ASC")
    fun getCarsWithoutInputs() : LiveData<List<CarRecord>>

    //Search WITH Inputs (Car will exit)
    @Query("SELECT * FROM car WHERE idCar LIKE :plate || '%' ORDER BY idCar ASC")
    fun searchCar(plate: String) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE type IN (:type) ORDER BY idCar ASC")
    fun searchCar(type: List<Int>) : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE idCar LIKE :plate || '%' AND type IN (:type) ORDER BY idCar ASC")
    fun searchCar(plate: String, type: List<Int>) : LiveData<List<Car>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.idCar = record.carId WHERE (idCar LIKE :plate || '%' AND record.dateOutput IS NULL) ORDER BY car.idCar ASC")
    fun searchCarsWithInputs(plate: String) : LiveData<List<CarRecord>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.idCar = record.carId WHERE (type IN (:type) AND record.dateOutput IS NULL) ORDER BY car.idCar ASC")
    fun searchCarsWithInputs(type: List<Int>) : LiveData<List<CarRecord>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car INNER JOIN record ON car.idCar = record.carId WHERE (idCar LIKE :plate || '%' AND type IN (:type) AND record.dateOutput IS NULL) ORDER BY car.idCar ASC")
    fun searchCarsWithInputs(plate: String, type: List<Int>) : LiveData<List<CarRecord>>

    //Search WITHOUT Inputs (Car will enter)
    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.carId WHERE ((record.carId IS NULL OR record.dateOutput IS NOT NULL) AND idCar LIKE :plate || '%') ORDER BY car.idCar ASC")
    fun searchCarsWithoutInputs(plate: String) : LiveData<List<CarRecord>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.carId WHERE ((record.carId IS NULL OR record.dateOutput IS NOT NULL) AND type IN (:type)) ORDER BY car.idCar ASC")
    fun searchCarsWithoutInputs(type: List<Int>) : LiveData<List<CarRecord>>

    @Query("SELECT car.idCar, car.minutes, car.type, record.idRecord, record.carId, record.dateInput, record.dateOutput FROM car LEFT JOIN record ON car.idCar = record.carId WHERE ((record.carId IS NULL OR record.dateOutput IS NOT NULL) AND idCar LIKE :plate || '%' AND type IN (:type)) ORDER BY car.idCar ASC")
    fun searchCarsWithoutInputs(plate: String, type: List<Int>) : LiveData<List<CarRecord>>

}