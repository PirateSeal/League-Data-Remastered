package com.tcousin.leaguedataremastered

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bottom_bar.onTabSelected = { tab ->
            when (tab.title) {
                "Profile" -> showProfileFragment()
                "History" -> showHistoricFragment()
                "Friends" -> showFriendsFragment()
                else -> showProfileFragment()
            }
        }
    }

    private fun showProfileFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, ProfileFragment())
            .commit()
    }

    private fun showHistoricFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, HistoricFragment()).commit()
    }

    private fun showFriendsFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, FriendFragment())
            .commit()
    }
}