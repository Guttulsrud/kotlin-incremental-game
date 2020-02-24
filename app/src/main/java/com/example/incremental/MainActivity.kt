package com.example.incremental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.incremental.db.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val player = Player()
        val grandma = BonusEntity("Grandma", 1, 100, 0)
        player.addBonusEntity(grandma)
        grandmaButton.text = "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() +" points"


//        val dB = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()


        textView.text = "0"
        button.setOnClickListener {
            player.click()
            textView.text = player.getPoints().toString()
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
    }
}
