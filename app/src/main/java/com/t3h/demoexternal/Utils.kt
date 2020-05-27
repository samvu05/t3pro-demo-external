package com.t3h.demoexternal

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

class Utils{
    companion object {
        @JvmStatic
        @BindingAdapter("updateText")
        fun updateText(tv: TextView, value:String?){
            tv.text = value
        }

        @JvmStatic
        @BindingAdapter("updateDrawable")
        fun updateDrawable(iv: ImageView, id:Int){
            iv.setImageResource(id)
        }
    }
}