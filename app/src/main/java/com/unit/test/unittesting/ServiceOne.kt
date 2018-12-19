package com.unit.test.unittesting

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @author lisen
 * @since 12-06-2018
 */
class ServiceOne : Service() {
    var TAG = "ServiceOne"

    var lifeCycle: String = "null"

    override fun onBind(intent: Intent?): IBinder? {
        lifeCycle = "onBind"
        Log.i(TAG, lifeCycle)
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        lifeCycle = "onStartCommand"
        Log.i(TAG, lifeCycle)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        lifeCycle = "onCreate"
        Log.i(TAG, lifeCycle)

        super.onCreate()
    }

    override fun onDestroy() {
        lifeCycle = "onDestroy"
        Log.i(TAG, lifeCycle)
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        lifeCycle = "onUnbind"
        Log.i(TAG, lifeCycle)
        return super.onUnbind(intent)
    }

}