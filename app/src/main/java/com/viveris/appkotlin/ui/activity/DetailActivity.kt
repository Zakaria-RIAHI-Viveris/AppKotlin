package com.viveris.appkotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.viveris.appkotlin.R
import com.viveris.appkotlin.extention.dateToString
import com.viveris.appkotlin.extention.toast
import com.viveris.appkotlin.model.BadgeCounts
import com.viveris.appkotlin.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_EXTRA_USER = "BUNDLE_EXTRA_USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        intent.extras?.run {
            val user = getSerializable(BUNDLE_EXTRA_USER) as User
            initializeView(user)
        }
        val badge: BadgeCounts = BadgeCounts(null, 10)

    }

    private fun initializeView(user: User) {
        text_view_user_alias.text = user.displayName
        text_view_user_location.text = user.location
        text_view_user_creation_date.text = getString(R.string.creation_date, user.creationDate.dateToString())
        val badgeCounts = user.badgeCounts
        if (badgeCounts != null) {
            text_view_gold_badge.text = badgeCounts.gold.toString()
            text_view_silver_badge.text = badgeCounts.silver.toString()
            text_view_bronze_badge.text = badgeCounts.bronze.toString()
        }
        text_view_user_website.setOnClickListener { openBrowser(user) }
        text_view_bronze_badge.setOnClickListener { toast("something") }
    }

    private fun openBrowser(user: User) {
        val browserIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(user.websiteUrl)
        }
        startActivity(browserIntent)
    }
}
