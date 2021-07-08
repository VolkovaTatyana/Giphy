package tatyana.volkova.app.giphy.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tatyana.volkova.app.giphy.R
import tatyana.volkova.app.giphy.domain.model.Gif

class GifView (layoutInflater: LayoutInflater, container: ViewGroup) {
    val view: View = layoutInflater.inflate(R.layout.item_gif_layout, container, false)

    fun bind(gif: Gif) {

    }
}