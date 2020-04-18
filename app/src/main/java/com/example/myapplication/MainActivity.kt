package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.ViewModel.MyObserver
import com.example.myapplication.ViewModel.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myObserver: MyObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myObserver = MyObserver()
        lifecycle.addObserver(myObserver)
    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Hello, toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe(view: View) {
        val countString = textView2.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        textView2.text = count.toString()
    }

    fun randomMe(view: View) {
        val randomIntent = Intent(this, SecondActivity::class.java)
        val countString = textView2.text.toString()
        var count: Int = Integer.parseInt(countString)

        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
       startActivity(randomIntent)
    }

    private fun toastMeState(message: String){
        Toast.makeText(this, "${lifecycle.currentState}, $message", Toast.LENGTH_LONG).show()
    }

    /*создание*/
    override fun onStart() {
        super.onStart()
        toastMeState("ON_START")
    }
    /*запущено - отражение активити пользователю*/
    override fun onResume() {
        super.onResume()
        toastMeState("ON_RESUME")
    }
    /*процесс восстановления завершен*/
    override fun onPostResume() {
        super.onPostResume()
        toastMeState("ON_POST_RESUME")
    }
    /*приложение видимо, но не работает*/
    override fun onPause() {
        super.onPause()
        toastMeState("ON_PAUSE")
    }
    /*не видимо, но еще не уничтожено*/
    override fun onStop() {
        super.onStop()
        toastMeState("ON_STOP")
    }
    /*после onStop, если будет возврат*/
    override fun onRestart() {
        super.onRestart()
        toastMeState("ON_RESTART")
    }
    /*после onStop, перед уничтожением*/
    override fun onDestroy() {
        super.onDestroy()
        toastMeState("ON_DESTROY")
    }
}
