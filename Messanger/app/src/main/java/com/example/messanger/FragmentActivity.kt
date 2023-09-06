package com.example.messanger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentActivity : AppCompatActivity() {

    lateinit var bottom_nav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        bottom_nav = findViewById(R.id.Bottom_Nav)
        setFragment(MessageFragment())

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_message -> {
                    setFragment(MessageFragment())
                }
                R.id.item_friends -> {
                    setFragment(FriendsFragment())
                }
            }
            true
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_profile ->  {intent = Intent(this, ProfileActivity::class.java);
            startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun setFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout,fragment,null).commit()
    }
}