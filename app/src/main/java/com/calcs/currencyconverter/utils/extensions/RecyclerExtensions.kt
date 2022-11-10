package com.calcs.currencyconverter.utils.extensions

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDefaultVerticalDivider(){
    addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
}