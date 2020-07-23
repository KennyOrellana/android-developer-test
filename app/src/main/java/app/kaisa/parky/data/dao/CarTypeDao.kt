package app.kaisa.parky.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import app.kaisa.parky.data.models.CarType

@Dao
interface CarTypeDao {

    @Insert
    fun insertCarType(carType: CarType)

    @Delete
    fun deleteCarType(carType: CarType)

    @Update
    fun updateCarType(carType: CarType)
}