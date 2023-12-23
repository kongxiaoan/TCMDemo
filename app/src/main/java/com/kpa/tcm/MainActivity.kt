package com.kpa.tcm

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.kpa.base.TCMBaseActivity
import com.kpa.base.utils.UIUtils
import com.kpa.player.event.Dispatcher
import com.kpa.player.event.Event
import com.kpa.tcm.core_sample.Simple
import com.kpa.tcm.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : TCMBaseActivity(), Dispatcher.EventListener {

    private lateinit var binding: ActivityMainBinding
    private var mShowActionBar = false
    private val TAG = "MainActivity_TAG"

    companion object {
        fun intentInto(activity: Activity, showActionBar: Boolean) {
            val intent = Intent(activity, MainActivity::class.java)
            val bundle = Bundle()
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UIUtils.setSystemBarTheme(
            this,
            Color.TRANSPARENT,
            true,
            true,
            Color.WHITE,
            true,
            false
        )
        var simple = Simple(Looper.getMainLooper(), this)
        binding.sampleText.setOnClickListener {
            thread {
                simple.test()
            }
        }
    }

    override fun onBackPressed() {
        if (mShowActionBar) {
            super.onBackPressed()
        } else {
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }
    }

    override fun onEvent(event: Event) {
        Log.d(TAG, "thread = ${Thread.currentThread().name} , event = ${event.toString()}")
    }
}