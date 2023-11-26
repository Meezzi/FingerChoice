package com.meezzi.fingerchoice.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.databinding.ViewDetailHomeInfoBinding

class DetailHomeInfoLayout(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    private val binding: ViewDetailHomeInfoBinding

    init {
        binding = ViewDetailHomeInfoBinding.inflate(LayoutInflater.from(context), this)
        context.obtainStyledAttributes(attrs, R.styleable.DetailHomeInfoLayout)
            .use { array ->
                val stringLabel = array.getResourceId(
                    R.styleable.DetailHomeInfoLayout_detailHomeInfoText,
                    0
                )
                val imageLabel = array.getResourceId(
                    R.styleable.DetailHomeInfoLayout_detailHomeInfoLabel,
                    0
                )
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