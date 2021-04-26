package com.example.tema2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tema2.R
import com.example.tema2.fragments.UsersFragment
import com.example.tema2.fragments.AlbumsFragment
import com.example.tema2.interfaces.FragmentCommunication
import com.example.tema2.models.User

class MainActivity : AppCompatActivity(), FragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUsersFragment()
    }

    private fun addUsersFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = UsersFragment::class.java.name
        val addTransaction = transaction.add(
                R.id.frame_layout, UsersFragment.newInstance("", ""), tag
        )
        addTransaction.commit()
    }

    override fun addAlbumsFragment(user: User) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = AlbumsFragment::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_layout, AlbumsFragment.newInstance("", ""), tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }
}