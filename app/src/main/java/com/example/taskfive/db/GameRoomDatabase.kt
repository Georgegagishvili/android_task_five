package com.example.taskfive.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskfive.configs.Config
import com.example.taskfive.dao.GameDao
import com.example.taskfive.models.Game

@Database(entities = [Game::class], version = Config.DATABASE_VERSION)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    Config.DATABASE_NAME
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}