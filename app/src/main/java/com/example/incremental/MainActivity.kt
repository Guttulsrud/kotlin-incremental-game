package com.example.incremental

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var player = Player()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val grandma = BonusEntity("Grandma", 1, 100, 0)
        player.addBonusEntity(grandma)
        grandmaButton.text = "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() +" points"

        textView.text = "0"
        button.setOnClickListener {
            player.click()
            textView.text = player.getPoints().toString()
        }

        store_button.setOnClickListener {
            val intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
        stats_button.setOnClickListener {
            val intent = Intent(this, StatsActivity:: class.java)
            startActivity(intent)
        }
        grandmaButton.setOnClickListener {
            if (player.getPoints() >= player.getBonusEntities().elementAt(0).getPrice()) {
                player.boughtItem(player.getBonusEntities().elementAt(0).getPrice())
                textView.text = player.getPoints().toString()
                player.getBonusEntities().elementAt(0).boughtNew(1)
                grandmas.text = player.getBonusEntities().elementAt(0).getAmoutOwned().toString()
                grandmaButton.text = "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() +" points"
            }
        }
        this.openFileOutput("userFile", Context.MODE_PRIVATE).use {
            it.write(gson.toJson(player).toByteArray())
        }

    }

    override fun onPause() {
        this.openFileOutput("userFile", Context.MODE_PRIVATE).use {
            it.write(gson.toJson(player).toByteArray())
        }

        super.onPause()
    }


    override fun onResume() {



        val loadedUser = this.openFileInput("userFile").bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
        player = gson.fromJson(loadedUser, Player::class.java)
        grandmaButton.text = "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() +" points"
        textView.text = player.getPoints().toString()
        grandmas.text = player.getBonusEntities().elementAt(0).getAmoutOwned().toString()

        super.onResume()
    }
}
