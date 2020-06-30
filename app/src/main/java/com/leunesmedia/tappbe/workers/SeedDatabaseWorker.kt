package com.leunesmedia.tappbe.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.leunesmedia.tappbe.model.Beer
import com.leunesmedia.tappbe.data.db.TappDatabase
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        val db = TappDatabase.getTappDatabaseInstance(applicationContext)
        db.beerDao().insert(
            listOf(
                Beer(
                    name = "Stella Artois",
                    beerId = "13fdf",
                    description = "Lekker biertje",
                    price = 2.50,
                    volume = 33.00
                )
            )
        )
        Result.success()
    }
}