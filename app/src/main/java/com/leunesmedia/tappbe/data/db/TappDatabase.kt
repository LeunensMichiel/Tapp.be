package com.leunesmedia.tappbe.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.leunesmedia.tappbe.model.Beer
import com.leunesmedia.tappbe.data.db.dao.BeerDao
import com.leunesmedia.tappbe.workers.SeedDatabaseWorker

@Database(entities = [Beer::class], version = DatabaseMigrations.DB_VERSION, exportSchema = false)
public abstract class TappDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

    companion object {
        @Volatile
        private var INSTANCE: TappDatabase? = null

        fun getTappDatabaseInstance(context: Context): TappDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        )
                            .also { INSTANCE = it }
                }
        }

        private fun buildDatabase(context: Context): TappDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TappDatabase::class.java,
                "tapp_database"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            }).fallbackToDestructiveMigration()
//                .addMigrations(*DatabaseMigrations.MIGRATIONS)
                .build()
        }
    }
}