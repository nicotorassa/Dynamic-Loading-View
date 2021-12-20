package com.artificioo.dynamicloadingview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artificioo.dynamicloadingview.view.DynamicLoadingView

class DLVMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val icons = intArrayOf(
            R.drawable.ic_launcher_foreground,
        )
        findViewById<DynamicLoadingView>(R.id.dynamic_background).setIcons(icons)

        findViewById<DynamicLoadingView>(R.id.dynamic_background).start()
    }
}