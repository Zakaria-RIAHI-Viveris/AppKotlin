package com.viveris.appkotlin.ui.view

import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import com.viveris.appkotlin.extention.changeVisibility

/**
 * This class manage the display of a loading overlay
 */
class ProgressBarManager {

    /**
     * display/hide a loading overlay
     * @param isLoading to know if we display or hide the frame layout
     * @param progressBarHolder the pregress bar id
     */
    fun onLoaderStateChange(isLoading: Boolean, progressBarHolder: FrameLayout?) {
        progressBarHolder?.apply {
            animation = when (isLoading) {
                true -> AlphaAnimation(0f, 1f)
                else -> AlphaAnimation(1f, 0f)
            }.also {
                it.duration = 500
            }
            changeVisibility(isLoading)
        }
    }
}
