package app.kaisa.parky.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class CarType(
    @PrimaryKey(autoGenerate = true)
    val idType: Int,
    val name: String,
    val price: Double) {
    @Ignore var isChecked: Boolean = true
}