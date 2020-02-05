package com.example.arprojekti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.HitTestResult
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

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
        arFragment.setPosition(position)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            arFragment)
            .addToBackStack(null )
            .commit()


    }

}
