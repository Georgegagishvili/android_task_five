package com.example.taskfive.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfive.models.Game
import com.example.taskfive.adapters.GameRecyclerAdapter
import com.example.taskfive.db.GameRoomDatabase
import com.example.taskfive.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var gamesDB: GameRoomDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var games: ArrayList<Game>
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gamesDB = GameRoomDatabase.getDatabase(this)

        init()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun init() {
        recyclerView = binding.mainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        games = ArrayList()
        updateData()

        binding.seedFloatingButton.setOnClickListener {
            gamesDB.gameDao().deleteEverything()
            seed()
        }
        binding.addFloatingButton.setOnClickListener {
            onAddGame()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun seed() {
        games.clear()
        for (game in Game.dummyData) {
            games.add(game)
        }

        recyclerView.adapter = GameRecyclerAdapter(games)

        GlobalScope.launch(Dispatchers.IO) {
            gamesDB.gameDao().deleteEverything()
            for (game in games) {
                gamesDB.gameDao().insert(
                    game
                )
            }
        }
    }

    private fun onAddGame() {
        val intent = Intent(this, AddOrEditGameActivity::class.java)
        startActivity(intent)
    }

    private fun updateData() {
        val dataFromDB = ArrayList(gamesDB.gameDao().getAll())
        if (dataFromDB != games) {
            games = ArrayList(gamesDB.gameDao().getAll())
            recyclerView.adapter = GameRecyclerAdapter(games)
        }
    }
}