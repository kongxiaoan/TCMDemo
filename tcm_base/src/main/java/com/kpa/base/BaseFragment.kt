package com.kpa.base

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 *
 * @author: kpa
 * @date: 2023/12/22
 * @description:
 */
class BaseFragment : Fragment() {
    private var mUserExiting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserExiting = false
        initBackPressedHandler()
    }

    private fun initBackPressedHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!onBackPressed()) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    } else {
                        isEnabled = true
                    }
                }

            })
    }

    fun onBackPressed(): Boolean {
        return false
    }

}