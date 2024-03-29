package com.artificioo.dynamicloadingview.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.artificioo.dynamicloadingview.R

class DynamicLoadingView : LinearLayout {
    private var ICONS: IntArray? = null
    private var COLORS: IntArray? = null

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        inflate(context, R.layout.view_loading, this)
    }

    fun start() {
        val parentView = getChildAt(0) as LinearLayout
        for (i in 0 until parentView.childCount) {
            val dynamicRow = parentView.getChildAt(i) as LinearLayout
            for (j in 0 until dynamicRow.childCount) {
                getRandomIcon()?.let { icon ->
                    val dynamicImage = dynamicRow.getChildAt(j) as ImageView
                    dynamicImage.alpha = 0f
                    dynamicImage.setImageResource(icon)
                    getRandomColor()?.let { color ->
                        dynamicImage.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context, color
                            )
                        )
                    }
                    makeViewAnimation(dynamicImage)
                }
            }
        }
    }

    fun setIcons(icons: IntArray) {
        ICONS = icons
    }

    fun setColors(colors: IntArray) {
        COLORS = colors
    }

    private fun makeViewAnimation(view: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        fadeInAnimation.startOffset = (Math.random() * 1000).toLong()
        fadeInAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                view.startAnimation(fadeOutAnimation)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        fadeOutAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                getRandomIcon()?.let { icon ->
                    view.setImageResource(icon)
                    getRandomColor()?.let { color ->
                        view.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context, color
                            )
                        )
                    }
                    view.startAnimation(fadeInAnimation)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        view.alpha = 1f
        view.startAnimation(fadeInAnimation)
    }

    fun getRandomIcon(): Int? {
        ICONS?.let {
            if (it.isNotEmpty()) {
                return it[(Math.random() * it.size).toInt()]
            }
        }
        return null
    }

    fun getRandomColor(): Int? {
        COLORS?.let {
            if (it.isNotEmpty()) {
                return it[(Math.random() * it.size).toInt()]
            }
        }
        return null
    }
}