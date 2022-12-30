package com.example.galgotialfest.ui.adapter

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galgotialfest.databinding.DetailsUserListingBinding
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.ui.listners.FragmentDetailsListingListener
import kotlinx.coroutines.flow.callbackFlow

class DetailsListingAdapter(val callback: FragmentDetailsListingListener): RecyclerView.Adapter<DetailsListingAdapter.DetailsViewHolder>() {
    var list:MutableList<DetailsUser> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):DetailsListingAdapter.DetailsViewHolder{
        val binding =
            DetailsUserListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsListingAdapter.DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsListingAdapter.DetailsViewHolder, position: Int) {
       holder.binding.detailsUser=list.get(position)
        holder.binding.callback=callback
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateUI(list:List<DetailsUser>){
        this.list=list.toMutableList()
        notifyDataSetChanged()
    }
    class DetailsViewHolder(var binding: DetailsUserListingBinding): RecyclerView.ViewHolder(binding.root)

}


