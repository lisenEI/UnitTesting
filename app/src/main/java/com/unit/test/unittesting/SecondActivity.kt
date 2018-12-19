package com.unit.test.unittesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    var TAG = "SecondActivity"

    var lifeCycle: String = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        lifeCycle = "onCreate"
        Log.i(TAG, lifeCycle)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        lifeCycle = "onSaveInstanceState"
        Log.i(TAG, lifeCycle + " " + outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        lifeCycle = "onRestoreInstanceState"
        Log.i(TAG, lifeCycle + " " + savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifeCycle = "onStart"
        Log.i(TAG, lifeCycle)

    }

    override fun onResume() {
        super.onResume()
        lifeCycle = "onResume"
        Log.i(TAG, lifeCycle)

    }

    override fun onPause() {
        super.onPause()
        lifeCycle = "onPause"
        Log.i(TAG, lifeCycle)

    }

    override fun onStop() {
        super.onStop()
        lifeCycle = "onStop"
        Log.i(TAG, lifeCycle)

    }

    override fun onRestart() {
        super.onRestart()
        lifeCycle = "onRestart"
        Log.i(TAG, lifeCycle)

    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycle = "onDestroy"
        Log.i(TAG, lifeCycle)

    }
}
