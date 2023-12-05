package com.meezzi.fingerchoice.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "review"
)
data class Review(
    @ColumnInfo val restaurant: String,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val score: Float,
    @ColumnInfo val taste: String,
    @ColumnInfo val date: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
