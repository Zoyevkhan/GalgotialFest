package com.example.galgotialfest.binding

import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galgotialfest.R
import com.example.galgotialfest.model.*
import com.example.galgotialfest.ui.adapter.DetailsListingAdapter
import com.example.galgotialfest.ui.adapters.DashboardAdapter
import com.example.galgotialfest.ui.adapters.GameListingAdapter
import com.example.galgotialfest.ui.listners.DashboardFragmentListner
import com.example.galgotialfest.ui.listners.DashboardMenuListingFragmenrListnere
import com.example.galgotialfest.ui.listners.FragmentDetailsListingListener
import com.example.galgotialfest.util.LoadingState

@BindingAdapter("gif")
fun ImageView.loadImageFromDrawable(image: Int?) {
    Log.d("zoyeb", "loadImageFromDrawable:" + image)
    image?.let {
        Glide.with(this.context).load(image).into(this)
    }
}

@BindingAdapter("loadDrawable")
fun CardView.loadDrawable(drawable: UserTypeDrawable?) {
    drawable?.let {
        this.background = this.context.resources.getDrawable(it.drawable)
    }
}

@BindingAdapter("setAdapter", "listner")
fun RecyclerView.setAdapter(
    liveData: MutableLiveData<List<CommonModel>>,
    listner: DashboardFragmentListner
) {
    this.adapter = this.adapter ?: DashboardAdapter(this.context, listner)
    ((this.adapter) as DashboardAdapter).updateUI(liveData.value ?: mutableListOf())
    this.layoutManager = this.layoutManager ?: GridLayoutManager(this.context, 2)

}

@BindingAdapter("setAdapterforListing", "listener")
fun RecyclerView.setAdapterForListing(
    liveData: MutableLiveData<List<CommonModel>>,
    listnere: DashboardMenuListingFragmenrListnere
) {
    this.adapter = this.adapter ?: GameListingAdapter(this.context, listnere)
    ((this.adapter) as GameListingAdapter).updateUI(liveData.value ?: mutableListOf())
    this.layoutManager = this.layoutManager ?: LinearLayoutManager(this.context)

}

@BindingAdapter("gameData", "userType")
fun TextView.CustomVisibiloty(game: LiveData<GameModel>?, userType: Int) {
    game?.let { game ->
        var gamedatata = game.value
        if (userType == 0 && !gamedatata?.teachersCoordinators.isNullOrEmpty())
            this.visibility = View.GONE
        else if (userType == 1 && !gamedatata?.studentCoordinators.isNullOrEmpty())
            this.visibility = View.GONE
        else if (userType == 2 && !gamedatata?.participants.isNullOrEmpty())
            this.visibility = View.GONE
        else
            this.visibility = View.VISIBLE
    }
}

@BindingAdapter("loadingstate")
fun ProgressBar.CustomVisibility(livedata: LiveData<LoadingState>?) {
    livedata?.value?.let { state->
        when(state){
            is LoadingState.isLoading->this.visibility=View.VISIBLE
            is LoadingState.Sucess ->Toast.makeText(this.context,state.message,Toast.LENGTH_SHORT).show()
            is LoadingState.Fail ->Toast.makeText(this.context,state.message,Toast.LENGTH_SHORT).show()
        }
    }
}
@BindingAdapter("setAdapterforDetailsListing", "usertype1","detailsActivityListener")
fun RecyclerView.setAdapterForListing(game: MutableLiveData<GameModel>?, userType: Int,callback: FragmentDetailsListingListener) {
    this.adapter = this.adapter ?: DetailsListingAdapter(callback)
    var list = mutableListOf<DetailsUser>()
    game?.let { game ->
        var gamedatata = game.value
        if (userType == 0 && !gamedatata?.teachersCoordinators.isNullOrEmpty())
            list = gamedatata?.teachersCoordinators ?: mutableListOf()
        else if (userType == 1 && !gamedatata?.studentCoordinators.isNullOrEmpty())
            list = gamedatata?.studentCoordinators ?: mutableListOf()
        else if (userType == 2 && !gamedatata?.participants.isNullOrEmpty())
            list = gamedatata?.participants ?: mutableListOf()

    }
    ((this.adapter) as DetailsListingAdapter).updateUI(list)
    this.layoutManager = this.layoutManager ?: LinearLayoutManager(this.context)
}
