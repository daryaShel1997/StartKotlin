package com.example.myapplication.ViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.user_item.view.*
import java.util.ArrayList

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users: List<User> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: UserHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount() = users.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(itemView) {
            userName.text = user.name
            userDescription.text = user.description
        }
    }
}