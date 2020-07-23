package app.kaisa.parky.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.kaisa.parky.data.dao.CarDao
import app.kaisa.parky.data.dao.CarTypeDao
import app.kaisa.parky.data.dao.RecordDao
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.models.Record

@Database(
    entities = [Car::class, CarType::class, Record::class],
    version = 1
)
abstract class ParkyDatabase : RoomDatabase(){

    abstract fun carDao(): CarDao
    abstract fun carTypeDao(): CarTypeDao
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: ParkyDatabase? = null

        fun getDB(context: Context): ParkyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ParkyDatabase::class.java,
                    "parky"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}