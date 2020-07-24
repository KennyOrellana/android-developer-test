package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val id: String,
    val type: Int,
    var minutes: Int = 0){

    @Ignore var dateInput: Long? = null
    @Ignore var dateOutput: Long? = null
}