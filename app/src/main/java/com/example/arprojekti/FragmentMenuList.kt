package com.example.arprojekti

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.menu_item.view.*


class FragmentMenuList : Fragment() {

    internal var activityCallBack: FragmentMenuListListener? = null
    private lateinit var menuRecycler: RecyclerView
    private val menuItems = MenuModel.menuItems

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        menuRecycler= view.findViewById(R.id.my_recycler_view)
        menuRecycler.apply{

            adapter = MenuAdapter(menuItems)
            layoutManager = LinearLayoutManager(context)
        }
        return view
    }

    interface FragmentMenuListListener {
        fun onElementClick(position:Int)
    }

    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as FragmentMenuListListener
    }



    private inner class MenuAdapter (private val myDataset: List<MenuItem>) :
        RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {

            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_item, parent, false)
            // set the view's size, margins, paddings and layout parameters
            return MyViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            val meal = myDataset[position]

            //holder.itemView.meal_image.setImageDrawable(resources.getDrawable(R.drawable.falafel))
            holder.itemView.txt_meal_name.text = meal.itemName
            holder.itemView.txt_meal_type.text = meal.itemType

            holder.itemView.setOnClickListener {
                // set "OnItemClickListener", when user click on ListView element
                Log.d("TAG","Position menuFragment passed: $position")
                activityCallBack!!.onElementClick(position)
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size
    }
}


