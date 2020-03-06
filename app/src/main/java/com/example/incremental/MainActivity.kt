package com.example.incremental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.incremental.db.UserDAO
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var player = Player()


    private lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grandma = BonusEntity("Grandma", 1, 100, 0)
        player.addBonusEntity(grandma)
        grandmaButton.text =
            "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() + " points"

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
                grandmaButton.text =
                    "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() + " points"
            }
        }

    }

    override fun onPause() {


        userDao = UserDAO(this)
        var user = userDao.getAllUsers()[0]

        userDao.update(
            user,
            player.getPoints(),
            player.getClickStrength(),
            player.getTotalPoints(),
            player.getTotalClicks()
        )



        super.onPause()
    }


    override fun onResume() {
        userDao = UserDAO(this)

        val user = userDao.getAllUsers()[0]

        player.updateFromDb(user.points, user.clickStrength, user.totalPoints, user.totalClicks)
        grandmaButton.text =
            "Grandma: " + player.getBonusEntities().elementAt(0).getPrice().toString() + " points"
        textView.text = player.getPoints().toString()
        grandmas.text = player.getBonusEntities().elementAt(0).getAmoutOwned().toString()

        super.onResume()
    }

}
