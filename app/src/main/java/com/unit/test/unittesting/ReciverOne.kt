package com.unit.test.unittesting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

/**
 * @author lisen
 * @since 12-06-2018
 */
class ReciverOne : BroadcastReceiver() {

    companion object {
        const val EXTRA_MSG = "extra_msg"
        const val ACTION = "com.unit.test.unittesting.reciverone"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null) {
            var msg = intent.getStringExtra(EXTRA_MSG)
            PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putString(EXTRA_MSG, msg)
                .apply()
        }
    }

}