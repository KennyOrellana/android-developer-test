package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    val idRecord: Int,
    val carId: String, //TODO this should be Car
    val dateInput: Long,
    var dateOutput: Long?) {

    constructor(car: String) : this(0, car, System.currentTimeMillis(), null)
}