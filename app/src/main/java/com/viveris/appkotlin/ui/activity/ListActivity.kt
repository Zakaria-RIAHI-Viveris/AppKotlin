package com.viveris.appkotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.viveris.appkotlin.AppKotlinApplication
import com.viveris.appkotlin.R
import com.viveris.appkotlin.extention.toast
import com.viveris.appkotlin.model.User
import com.viveris.appkotlin.model.Users
import com.viveris.appkotlin.network.RetrofitNetworkManager
import com.viveris.appkotlin.ui.adapter.UsersRecyclerAdapter
import com.viveris.appkotlin.ui.listener.IListUserListener
import com.viveris.appkotlin.ui.view.ProgressBarManager
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), IListUserListener {

    private var progressBarManager: ProgressBarManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        progressBarManager = ProgressBarManager()
        initializeData()
    }

    private fun initializeData() {
        progressBarManager?.onLoaderStateChange(true, progress_bar_holder)
        RetrofitNetworkManager(this).fetchUserFromNetwork(application as AppKotlinApplication)
    }

    override fun displayResult(body: Users) {
        progressBarManager?.onLoaderStateChange(false, progress_bar_holder)
        recycler_view_users?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = UsersRecyclerAdapter(body.items!!, applicationContext, this@ListActivity)
        }
        val vue: View? = null
        (vue as? TextView)?.apply {
            text = "blabk"
        }
    }

    override fun displayFailure() {
        progressBarManager?.onLoaderStateChange(false, progress_bar_holder)
        toast("failure")
    }

    override fun onUserClicked(user: User) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.BUNDLE_EXTRA_USER, user)
        startActivity(detailIntent)
    }
}
