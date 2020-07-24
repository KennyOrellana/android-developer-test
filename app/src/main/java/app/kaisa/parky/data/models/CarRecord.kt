package app.kaisa.parky.data.models

import androidx.room.Embedded

data class CarRecord(
    @Embedded var car: Car,
    @Embedded var record: Record?
)