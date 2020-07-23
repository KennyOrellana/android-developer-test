package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val car: Int, //TODO this should be Car
    val dateInput: Long,
    var dateOutput: Long?)