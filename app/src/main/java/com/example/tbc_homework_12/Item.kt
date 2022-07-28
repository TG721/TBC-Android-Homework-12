package com.example.tbc_homework_12

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Item(
    val title: String,
    val description: String,
    val image: Int,
) :  Parcelable
val Items = mutableListOf<Item>(
    Item("nika1", "He is the lecturer", R.drawable.nika1),
    Item("nika2", "bla bla", R.drawable.nika2),
    Item("nika3", "bla bla", R.drawable.nika3),
    Item("nika4", "bla bla", R.drawable.nika4),
    Item("mentor", "bla bla", R.drawable.mentor),
    Item("davit1", "bla bla", R.drawable.davit1),
    Item("davit2", "bla bla", R.drawable.davit2),
    Item("lika1", "bla bla", R.drawable.lika1),
    Item("lika2", "bla bla", R.drawable.lika2),
    Item("lika3", "bla bla", R.drawable.lika3),
    Item("lika4", "bla bla", R.drawable.lika4),
    Item("gabidauri1", "bla bla", R.drawable.gabidauri1),
    Item("gabidauri2", "bla bla", R.drawable.gabidauri2),
    Item("gabidauri3", "bla bla", R.drawable.gabidauri3),
    Item("gabidauri4", "bla bla", R.drawable.gabidauri4),
    Item("eristavi1", "bla bla", R.drawable.eristavi1),
    Item("eristavi2", "bla bla", R.drawable.eristavi2),
    Item("eristavi3", "bla bla", R.drawable.eristavi3),
    Item("baia1", "bla bla", R.drawable.baia1),
    Item("janjgava1", "bla bla", R.drawable.janjgava1),
    Item("janjgava2", "bla bla", R.drawable.janjgava2),
    Item("janjgava3", "bla bla", R.drawable.janjgava3)
    )