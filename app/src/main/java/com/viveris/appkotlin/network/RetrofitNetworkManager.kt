package com.viveris.appkotlin.network

import com.viveris.appkotlin.AppKotlinApplication
import com.viveris.appkotlin.model.Users
import com.viveris.appkotlin.ui.listener.IListUserListener

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RetrofitNetworkManager(private val listener: IListUserListener) {

    fun fetchUserFromNetwork(application: AppKotlinApplication) {
        val requestInterface = application.retrofit!!.create(RequestInterface::class.java)
        requestInterface.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<DisposableSingleObserver<Users>>(object : DisposableSingleObserver<Users>() {
                    override fun onSuccess(users: Users) {
                        when(users.items != null) {
                            true -> listener.displayResult(users)
                            else -> listener.displayFailure()
                        }
                    }

                    override fun onError(e: Throwable) {
                        listener.displayFailure()
                    }
                })
    }
}
