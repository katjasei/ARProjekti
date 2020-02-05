package com.example.arprojekti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), FragmentMenuList.FragmentMenuListListener {

    //Create a new Fragment to be placed in the activity layout
    private val menuListFragment = FragmentMenuList()
    private val arFragment = ARFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id. fragment_container, menuListFragment)
            .commit()
    }

    override fun onElementClick(position: Int) {
        Log.d("TAG", "Position Main Activity got: $position")
        // pass position to arFragment
        arFragment.setPosition(position)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            arFragment)
            .addToBackStack(null )
            .commit()
    }
}
