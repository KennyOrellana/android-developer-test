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
}