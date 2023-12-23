package com.kpa.tcm

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.kpa.base.TCMBaseActivity

class SplashActivity : TCMBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isTaskRoot) {
            val intent = intent
            intent?.run {
                if ((intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action)) {
                    finish()
                    return
                }
            }
        }
        initSystemBar()
        MainActivity.intentInto(this, false)
        finish()
    }

    private fun initSystemBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        window.decorView.systemUiVisibility = flags
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.WHITE
    }
}