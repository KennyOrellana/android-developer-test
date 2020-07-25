package app.kaisa.parky.data.dao

import androidx.room.*
import app.kaisa.parky.data.models.Record

@Dao
interface RecordDao {

    @Insert
    fun insertRecord(record: Record)

    @Delete
    fun deleteRecord(record: Record)

    @Update
    fun updateRecord(record: Record)

    @Query("DELETE FROM record WHERE carId IN (SELECT carId FROM record INNER JOIN car ON carId = car.idCar WHERE car.type = 1)")
    fun deleteRecordsOfficial()
}