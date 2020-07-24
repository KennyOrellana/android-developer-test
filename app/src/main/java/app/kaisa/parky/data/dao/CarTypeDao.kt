package app.kaisa.parky.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.kaisa.parky.data.models.CarType

@Dao
interface CarTypeDao {

    @Insert
    fun insertCarType(carType: CarType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarTypes(movies: List<CarType>)

    @Delete
    fun deleteCarType(carType: CarType)

    @Update
    fun updateCarType(carType: CarType)

    @Query("SELECT * FROM cartype ORDER BY idType ASC")
    fun getCarTypes() : LiveData<List<CarType>>
}