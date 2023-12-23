package com.kpa.tcm.core_sample

import android.os.Looper
import com.kpa.player.event.Dispatcher

/**
 *
 * @author: kpa
 * @date: 2023/12/22
 * @description:
 */
class Simple {

    private var dispatcher: Dispatcher? = null

    constructor(eventLooper: Looper, eventListener: Dispatcher.EventListener) {
        this.dispatcher = Dispatcher(eventLooper)
        this.dispatcher?.addEventListener(eventListener)
    }

    fun test() {
        dispatcher?.obtain(Test::class.java, this)?.dispatch()
    }
}