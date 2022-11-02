package com.example.taskfive.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskfive.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM GAMES")
    fun getAll(): List<Game>

    @Query("SELECT * FROM GAMES WHERE id == (:id)")
    fun getById(id: Int): Game

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM GAMES")
    fun deleteEverything()

    @Delete
    fun deleteItemById(game : Game)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: Game)

}
