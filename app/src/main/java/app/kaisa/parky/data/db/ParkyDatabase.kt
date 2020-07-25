package app.kaisa.parky.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.kaisa.parky.data.dao.CarDao
import app.kaisa.parky.data.dao.CarTypeDao
import app.kaisa.parky.data.dao.RecordDao
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.models.Record
import java.util.concurrent.Executors

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
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute {
                                getDB(context).carTypeDao().insertCarTypes(PREPOPULATE_DATA_CAR_TYPE)
                                getDB(context).carDao().insertCars(PREPOPULATE_DATA_CAR)
                            }
                        }
                    })
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        const val CAR_TYPE_OFICIAL = 1;
        const val CAR_TYPE_RESIDENT = 2;
        const val CAR_TYPE_NON_RESIDENT = 3;

        private val PREPOPULATE_DATA_CAR_TYPE = listOf(
            CarType(CAR_TYPE_OFICIAL, "Oficial",0.0),
            CarType(CAR_TYPE_RESIDENT, "Residente",0.05),
            CarType(CAR_TYPE_NON_RESIDENT, "Visitante",0.5)
        )

        private val PREPOPULATE_DATA_CAR = listOf(
            Car("NA1534", 1),
            Car("FA1534", 2),
            Car("HA1534", 1),
            Car("N5432", 2),
            Car("M5678", 2),
            Car("LK9834", 1)
        )
    }
}