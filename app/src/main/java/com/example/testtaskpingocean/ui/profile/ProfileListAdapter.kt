package com.example.testtaskpingocean.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskpingocean.R
import com.example.testtaskpingocean.data.entity.ProfileItem
import kotlinx.android.synthetic.main.item_profile_lists.view.*

class ProfileListAdapter : RecyclerView.Adapter<ProfileListAdapter.ViewHolder>() {
    private var profileList = listOf<ProfileItem>()

    fun submitList(list: ArrayList<ProfileItem>) {
        profileList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_profile_lists, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = profileList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = profileList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProfileItem) {
            itemView.text_title.setText(item.title)
            itemView.text_value.text = item.value
        }
    }
}