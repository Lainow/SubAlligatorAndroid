package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Content

@Dao
interface ContentDao {
    @Query("SELECT * FROM content")
    fun getAllContent(): List<Content>

    @Insert fun insertContent(vararg content: Content)

}