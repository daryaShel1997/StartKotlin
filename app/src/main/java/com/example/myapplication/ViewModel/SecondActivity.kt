package com.example.myapplication.ViewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.random.Random

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.R


class SecondActivity : AppCompatActivity() {

    companion object{
        const val TOTAL_COUNT = "total_count"
    }
    //инициализируем ViewModel ленивым способом
    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        showRandomNumber()

        buttonEnter.setOnClickListener{
            textView.text = editText.text
        }

        //инициализируем адаптер и присваиваем его списку
        val adapter = UserAdapter()
        userList.layoutManager = LinearLayoutManager(this)
        userList.adapter = adapter

        //подписываем адаптер на изменения списка
        userViewModel.getListUsers().observe(this, Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })
    }
    fun showRandomNumber(){
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val random = Random
        var randomInt = 0

        if (count > 0){
            randomInt = random.nextInt(count + 1)
        }
        textViewRandom.text=Integer.toString(randomInt)
        textViewLable.text = getString(R.string.random_heading, count)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putString("KEY", textView.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        textView.text = savedInstanceState?.getString("KEY")
    }

    //создаем меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //при нажатии пункта меню Refresh обновляем список
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {
                userViewModel.updateListUsers()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
