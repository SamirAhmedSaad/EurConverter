package com.calcs.currencyconverter.utils

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter(value = ["drawableStartText","size"])
fun setDrawableStartSize(textView: TextView , drawableStartSize : Drawable?, size : Int){

    drawableStartSize ?: return

    val size = dp2Px(textView.context,size)
    drawableStartSize.setBounds(0, 0, size, size)

    textView.setCompoundDrawablesRelative(drawableStartSize,null,null,null)

}

@BindingAdapter(value = ["drawableStartText","size"])
fun setDrawableStartSize(textView: TextView , drawableResStartSize : Int?, size : Int){

    drawableResStartSize ?: return

    val drawableStartSize = ContextCompat.getDrawable(textView.context,drawableResStartSize)
    drawableStartSize ?: return

    val size = dp2Px(textView.context,size)
    drawableStartSize.setBounds(0, 0, size, size)

    textView.setCompoundDrawablesRelative(drawableStartSize,null,null,null)

}