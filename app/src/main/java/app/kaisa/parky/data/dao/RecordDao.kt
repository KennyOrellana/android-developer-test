package app.kaisa.parky.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.models.Record

@Dao
interface RecordDao {

    @Insert
    fun insertRecord(record: Record)

    @Delete
    fun deleteRecord(record: Record)

    @Update
    fun updateRecord(record: Record)

    @Query("SELECT * FROM cartype ORDER BY idType ASC")
    fun getCarTypes() : LiveData<List<CarType>>
}