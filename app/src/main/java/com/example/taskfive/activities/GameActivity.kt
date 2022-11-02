package com.example.taskfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskfive.R
import com.example.taskfive.databinding.ActivityAddGameBinding
import com.example.taskfive.databinding.ActivityGameBinding
import com.example.taskfive.db.GameRoomDatabase
import com.example.taskfive.models.Game
import com.squareup.picasso.Picasso

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val db: GameRoomDatabase = GameRoomDatabase.getDatabase(this)
        val game: Game = db.gameDao().getById(intent.getIntExtra("gameId", 0))
        binding.gameTitle.text = game.title
        binding.gameDescription.text = game.description
        binding.gameReleaseYear.text = game.releaseYear
        Picasso.get().load(game.imageUrl).placeholder(R.drawable.ic_launcher_background)
            .into(binding.gameImage);
    }
}