package com.example.galgotialfest.ui.adapters

import android.content.ComponentCallbacks
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.galgotialfest.BR
import com.example.galgotialfest.databinding.GameItemLayoutBinding
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.ui.listners.DashboardMenuListingFragmenrListnere

class GameListingAdapter(val context:Context,val callbacks: DashboardMenuListingFragmenrListnere): RecyclerView.Adapter<GameListingAdapter.CommonViewHolder>() {
     var list:MutableList<CommonModel> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameListingAdapter.CommonViewHolder{
        val binding =
            GameItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameListingAdapter.CommonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameListingAdapter.CommonViewHolder, position: Int) {
        holder.binding.setVariable(BR.item,list.get(position))
        holder.binding.setVariable(BR.itemclicklistner,callbacks)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateUI(list:List<CommonModel>){
        this.list=list.toMutableList()
        notifyDataSetChanged()
    }
    class CommonViewHolder(var binding:ViewDataBinding ): RecyclerView.ViewHolder(binding.root)

}


