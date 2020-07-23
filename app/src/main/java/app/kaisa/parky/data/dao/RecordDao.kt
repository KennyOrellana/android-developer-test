package app.kaisa.parky.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
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