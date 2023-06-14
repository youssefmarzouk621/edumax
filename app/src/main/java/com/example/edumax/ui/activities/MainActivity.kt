package com.example.edumax.ui.activities



import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.edumax.R
import com.example.edumax.ui.fragments.FavorisFragment
import com.example.edumax.ui.fragments.HomeFragment
import com.example.edumax.ui.fragments.NotificationFragment
import com.example.edumax.ui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView





class MainActivity : AppCompatActivity() {


    var navMenu: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.Fcontainer, HomeFragment())
            .commit()


        navMenu = findViewById(R.id.bottomAppBar)

        navMenu!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.HomePage -> {
                    supportActionBar?.title = "Home"
                    setFragment(HomeFragment())
                    true
                }
                R.id.FavorisPage -> {
                    supportActionBar?.title = "Favoris"
                    setFragment(FavorisFragment())
                    true
                }
                R.id.NotifyPage -> {
                    supportActionBar?.title = "Notifications"
                    setFragment(NotificationFragment())
                    true
                }
                R.id.ProfilePage -> {
                    supportActionBar?.title = "Profile"
                    setFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Fcontainer, fragment)
                .setCustomAnimations(
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                ).commit()

        }
    }


}