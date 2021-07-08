package tatyana.volkova.app.giphy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tatyana.volkova.app.giphy.databinding.ItemGifLayoutBinding
import tatyana.volkova.app.giphy.domain.model.Gif

class GifAdapter : ListAdapter<Gif, GifAdapter.GifItemViewHolder>(GifItemDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GifItemViewHolder(
        ItemGifLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GifItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class GifItemViewHolder(private val binding: ItemGifLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif) {
            binding.apply {
                gif = item
                executePendingBindings()
            }
        }
    }
}