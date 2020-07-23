package app.kaisa.parky.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.kaisa.parky.data.models.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Delete
    fun deleteCar(car: Car)

    @Update
    fun updateCar(car: Car)

    @Query("SELECT * FROM car ORDER BY id ASC")
    fun getCars() : LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE id LIKE :plate || '%' ORDER BY id ASC")
    fun searchCar(plate: String) : LiveData<List<Car>>
}