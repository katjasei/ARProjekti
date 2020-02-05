package com.example.arprojekti

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


class ARFragment : Fragment() {

    private lateinit var fragment: ArFragment
    private var testRenderable: ModelRenderable? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.ar_fragment, container, false)
        val button = view.findViewById<Button>(R.id.btn_click)
        button.setOnClickListener{ addObject() }
        fragment = childFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment
        val modelUri = Uri.parse("valmisleipa3.sfb")
        val renderableFuture = ModelRenderable.builder()
            .setSource(fragment.context, modelUri)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            renderableFuture.thenAccept { testRenderable = it }
        }
        return view
    }


    private fun addObject() {
        val frame = fragment.arSceneView.arFrame
        val pt = getScreenCenter()
        val hits: List<HitResult>
        if (frame != null && testRenderable != null) {
            hits = frame.hitTest(pt.x.toFloat(), pt.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane) {
                    val anchor = hit!!.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(fragment.arSceneView.scene)
                    val mNode = TransformableNode(fragment.transformationSystem)
                    mNode.setParent(anchorNode)
                    mNode.renderable = testRenderable
                    mNode.select()
                    break }
            }
        }
    }

    private fun getScreenCenter(): android.graphics.Point {
        val vw = this.view!!
        return android.graphics.Point(vw.width / 2, vw.height / 2) }
}