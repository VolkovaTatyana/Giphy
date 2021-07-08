package tatyana.volkova.app.giphy.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import tatyana.volkova.app.giphy.domain.model.Gif

class GifItemDiffCallBack : DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title == newItem.title
                && oldItem.url == newItem.url
    }
}