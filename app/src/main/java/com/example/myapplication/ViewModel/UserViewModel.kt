package com.example.myapplication.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ViewModel.User
import com.example.myapplication.ViewModel.UserData

class UserViewModel : ViewModel() {

    var userList : MutableLiveData<List<User>> = MutableLiveData()

    //инициализируем список и заполняем его данными пользователей
    init {
        userList.value = UserData.getUsers()
    }

    fun getListUsers() = userList

    //для обновления списка передаем второй список пользователей
    fun updateListUsers() {
        userList.value = UserData.getAnotherUsers()
    }
}
