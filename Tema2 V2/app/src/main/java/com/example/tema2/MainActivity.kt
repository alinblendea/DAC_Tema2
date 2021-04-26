package com.example.tema2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
import com.example.tema2.fragments.UsersFragment
import com.example.tema2.fragments.AlbumsFragment
import com.example.tema2.fragments.PhotosFragment
import com.example.tema2.interfaces.FragmentCommunication
import com.example.tema2.models.Album
import com.example.tema2.models.User

class MainActivity : AppCompatActivity(), FragmentCommunication{
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
            R.id.frame_layout, UsersFragment.newInstance("",""), tag
        )
        addTransaction.commit()
    }

    override fun addAlbumsFragment(user: User) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = AlbumsFragment::class.java.name
        val replaceTransaction = transaction.replace(
            R.id.frame_layout, AlbumsFragment.newInstance("","", user), tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

    override fun addPhotosFragment(album: Album) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = PhotosFragment::class.java.name
        val replaceTransaction = transaction.replace(
            R.id.frame_layout, PhotosFragment.newInstance("","", album), tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }
}