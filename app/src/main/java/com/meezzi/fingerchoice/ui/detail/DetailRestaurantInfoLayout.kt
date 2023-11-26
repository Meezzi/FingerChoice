package com.meezzi.fingerchoice.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.databinding.ViewDetailRestaurantInfoBinding

class DetailRestaurantInfoLayout(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    private val binding: ViewDetailRestaurantInfoBinding

    init {
        binding = ViewDetailRestaurantInfoBinding.inflate(LayoutInflater.from(context), this)
        context.obtainStyledAttributes(attrs, R.styleable.DetailRestaurantInfoLayout)
            .use { array ->
                val stringLabel = array.getResourceId(R.styleable.DetailRestaurantInfoLayout_detailStringInfoLabel, 0)
                val imageLabel = array.getResourceId(R.styleable.DetailRestaurantInfoLayout_detailImageInfoLabel, 0)
                setStringInfoLabel(stringLabel)
                setImageInfoLabel(imageLabel)
            }
    }

    private fun setStringInfoLabel(labelResId: Int) {
        binding.tvLabel.text = context.getString(labelResId)
    }

    private fun setImageInfoLabel(labelResId: Int) {
        binding.ivLabel.setImageResource(labelResId)
    }
}