package com.tosanaji.tajilestari.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    fun main(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

    fun io(io: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            io()
        }
    }
}