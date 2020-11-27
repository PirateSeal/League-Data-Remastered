package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.bottom_bar as bottom_bar

class SecondActivity : AppCompatActivity() {

    lateinit var tv: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv = findViewById(R.id.second_title)
        bottom_bar.onTabSelected = { tab ->
            when (tab.title){
                "Profile" -> showProfileFragment()
                "Historic" -> showHistoricFragment()
                "Friends" -> showFriendsFragment()
                else -> showProfileFragment()
            }
        }
    }

    private fun showProfileFragment(){
        tv.text = "Profile";
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, ProfileFragment()).commit()
    }

    private fun showHistoricFragment(){
        tv.text = "Historic"
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, HistoricFragment() ).commit()
    }

    private  fun showFriendsFragment(){
        tv.text = "Friends"
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, FriendFragment()).commit();
    }

}