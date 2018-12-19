package com.unit.test.unittesting

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast(view: View) {
        Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
    }

    fun openSecondActivity(view: View) {
        var intent = Intent(this, SecondActivity().javaClass)
        intent.putExtra("source", MainActivity().javaClass.toString())
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun showDialog(view: View) {
//        android.support.v7.app.AlertDialog.Builder(this)
//            .setMessage("this is a dialog")
//            .setTitle("dialog title")
//            .create()
//            .show()
        AlertDialog.Builder(this)
            .setMessage("this is a dialog")
            .setTitle("dialog title")
            .create()
            .show()
    }

    fun addFragmentOne(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_fragment_container, FragmentOne(), FragmentOne().javaClass.simpleName)
            .commit()
    }
}
