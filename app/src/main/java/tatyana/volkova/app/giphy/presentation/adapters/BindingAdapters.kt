package tatyana.volkova.app.giphy.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import tatyana.volkova.app.giphy.R

@BindingAdapter("android:gifUrl")
fun ImageView.loadGif(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(this)
    }
}