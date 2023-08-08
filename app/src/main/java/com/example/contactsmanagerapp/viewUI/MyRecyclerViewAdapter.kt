package com.example.contactsmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanagerapp.R
import com.example.contactsmanagerapp.databinding.CardItemBinding
import com.example.contactsmanagerapp.room.User

class MyRecyclerViewAdapter
    (private val usersList:List<User>,
     //Usage of the lambda expression.
     //Unit is the returning type similar to the void.
     //Its major use case is for the singleton return.
     private val clickListener: (User)->Unit
     ): RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.
                    inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    //Implementing members will get us to this part.
    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

}

//ViewHolderClass
class MyViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(user: User, clickListener: (User) -> Unit){
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email

        binding.listItemLayout.setOnClickListener{
            clickListener(user)
        }
    }
}