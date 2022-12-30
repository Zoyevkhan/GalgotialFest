package com.example.galgotialfest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galgotialfest.databinding.DashboardItemBinding
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.ui.activity.DashboardActivity
import com.example.galgotialfest.ui.listners.DashboardFragmentListner

class DashboardAdapter(val context: Context,val listner:DashboardFragmentListner): RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    lateinit var list:MutableList<CommonModel>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardAdapter.DashboardViewHolder {
        val binding =
            DashboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardAdapter.DashboardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DashboardAdapter.DashboardViewHolder, position: Int) {
        holder.binding.commonItem=list.get(position)
        holder.binding.listner=listner
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateUI(list:List<CommonModel>){
        this.list=list.toMutableList()
        notifyDataSetChanged()
    }
    class DashboardViewHolder(var binding:DashboardItemBinding): RecyclerView.ViewHolder(binding.root)

}


