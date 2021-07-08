package tatyana.volkova.app.giphy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tatyana.volkova.app.giphy.databinding.ItemGifBinding
import tatyana.volkova.app.giphy.domain.model.Gif

class GifListAdapter : ListAdapter<Gif, GifListAdapter.GifViewHolder>(GifItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GifViewHolder(
            ItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class GifViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif) {
            binding.apply {
                gif = item
                executePendingBindings()
            }
        }
    }

    companion object {
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
    }
}