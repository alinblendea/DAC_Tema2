package com.example.tema2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tema2.R;
import com.example.tema2.adapters.StatePagerAdapter;
import com.example.tema2.fragments.AlbumsFragment;
import com.example.tema2.fragments.PhotosFragment;
import com.example.tema2.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    private StatePagerAdapter statePagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statePagerAdapter = new StatePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.fragment_container);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewpager){
        StatePagerAdapter adapter = new StatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UsersFragment(), "UsersFragment");
        adapter.addFragment(new AlbumsFragment(), "AlbumsFragment");
        adapter.addFragment(new PhotosFragment(), "PhotosFragment");

        viewpager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }
}