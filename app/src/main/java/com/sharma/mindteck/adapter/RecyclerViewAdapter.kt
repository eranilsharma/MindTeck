package com.sharma.mindteck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examp.project2.model.Model
import com.sharma.mindteck.databinding.CustomListLayoutBinding

class RecyclerViewAdapter(val context:Context,var list:ArrayList<Model>) : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customView=CustomListLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.CustomViewHolder {
        return CustomViewHolder(CustomListLayoutBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.CustomViewHolder, position: Int) {
                holder.customView.textLable.text=list.get(position).lable
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun filterList(dummyList: ArrayList<Model>) {
        this.list = dummyList
        notifyDataSetChanged()
    }
}