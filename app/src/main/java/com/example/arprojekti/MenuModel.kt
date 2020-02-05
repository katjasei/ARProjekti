package com.example.arprojekti


//Menu object mutable list where all items are added
//Items need to be added to SRFragment model switch too
object MenuModel {

    val menuItems: MutableList<MenuItem> = java.util.ArrayList()
    init {
        menuItems.add(MenuItem(R.drawable.laskiais, "Laskiaispulla", "Dessert"))
        menuItems.add(MenuItem(R.drawable.voileipa, "Leipä", "Main"))
        menuItems.add(MenuItem(R.drawable.kahvi, "Kahvi", "Drink"))
    }
}


// Model for menu item
class MenuItem(img:Int, itemName:String, itemType:String) {

    val img: Int
    val itemName:String
    val itemType:String


    init{
        this.img = img
        this.itemName = itemName
        this.itemType = itemType

    }
}