package tatyana.volkova.app.giphy.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import tatyana.volkova.app.giphy.R

@BindingAdapter("android:gifUrl")
fun ImageView.loadGif(url: String?) {

    Glide.with(this)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}