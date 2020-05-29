package com.viveris.appkotlin.ui.listener

import com.viveris.appkotlin.model.User
import com.viveris.appkotlin.model.Users

interface IListUserListener {

    fun onUserClicked(user: User)
    fun displayFailure()
    fun displayResult(body: Users)

}
