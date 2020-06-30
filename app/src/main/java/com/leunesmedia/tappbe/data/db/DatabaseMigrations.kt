package com.leunesmedia.tappbe.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.leunesmedia.tappbe.model.Beer

object DatabaseMigrations {
    const val DB_VERSION = 3

    val MIGRATIONS: Array<Migration>
    get() = arrayOf<Migration>(migration12())

    private fun migration12(): Migration = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Beer.TABLE_NAME} ADD COLUMN body TEXT")
        }

    }
}