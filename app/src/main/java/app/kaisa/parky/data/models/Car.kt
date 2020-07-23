package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val id: String,
    val type: Int,
    val minutes: Int = 0)