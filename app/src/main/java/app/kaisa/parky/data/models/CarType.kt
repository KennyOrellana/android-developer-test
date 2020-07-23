package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarType(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Double,
    val icon: String)