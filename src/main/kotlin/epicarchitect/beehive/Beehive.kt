package epicarchitect.beehive

import android.app.Application
import androidx.room.Room
import epicarchitect.beehive.database.AppDatabase

class Beehive : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: Beehive

        val database by lazy {
            Room.databaseBuilder(instance, AppDatabase::class.java, "epicarchitect.beehive")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}