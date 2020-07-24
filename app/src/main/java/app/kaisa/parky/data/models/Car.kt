package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val idCar: String,
    val type: Int,
    var minutes: Int = 0)