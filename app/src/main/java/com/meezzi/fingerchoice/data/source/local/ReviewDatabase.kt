package com.meezzi.fingerchoice.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.meezzi.fingerchoice.data.model.Converters

@Database(entities = [Review::class], version = 1)
@TypeConverters(Converters::class)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao

    companion object {
        @Volatile
        private var instance: ReviewDatabase? = null

        fun getDatabase(context: Context): ReviewDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                ReviewDatabase::class.java,
                "review"
            ).build().also {
                instance = it
            }
        }
    }
}