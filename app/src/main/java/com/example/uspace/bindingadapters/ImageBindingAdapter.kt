package com.example.uspace.bindingadapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl", "placeholder")
fun ImageView.loadImage(url: String, placeholder: Drawable) {
    Picasso.get().load(url).placeholder(placeholder).into(this)
}