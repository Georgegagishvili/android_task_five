package com.example.taskfive.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taskfive.models.Game
import com.example.taskfive.db.GameRoomDatabase
import com.example.taskfive.databinding.ActivityAddGameBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddOrEditGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGameBinding
    private lateinit var gamesDB: GameRoomDatabase
    private var contentId: Int? = null
    private var game: Game? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.addGameButton.setOnClickListener {
            onAction()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val intentData = intent.getIntExtra("gameId", -1)
        gamesDB = GameRoomDatabase.getDatabase(this)
        if (intentData != -1) {
            binding.addGameButton.text = "Edit"
            contentId = intentData
            game = gamesDB.gameDao().getById(contentId!!)
            binding.addGameTitle.setText(game!!.title)
            binding.addGameDescription.setText(game!!.description)
            binding.addGameImageUrl.setText(game!!.imageUrl)
            binding.addGameYear.setText(game!!.releaseYear)
        }

    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun onAction() {
        val title = binding.addGameTitle.text
        val desc = binding.addGameDescription.text
        val releaseYear = binding.addGameYear.text
        val imageUrl = binding.addGameImageUrl.text

        if (title.isEmpty() || desc.isEmpty() || releaseYear.isEmpty() || imageUrl.isEmpty()) {
            Snackbar.make(binding.root, "Please fill empty fields", Snackbar.LENGTH_LONG).show()
            return
        }

        game = Game(
            contentId,
            title.toString(),
            desc.toString(),
            releaseYear.toString(),
            imageUrl.toString(),
        )

        GlobalScope.launch(Dispatchers.IO) {
            if (contentId == null) {
                gamesDB.gameDao().insert(
                    game!!
                )
            } else {
                gamesDB.gameDao().updateGame(game!!)
            }

            finish()
        }
    }
}