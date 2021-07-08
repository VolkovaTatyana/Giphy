package tatyana.volkova.app.giphy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tatyana.volkova.app.giphy.databinding.ItemGifBinding
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.presentation.gif_list.GifListFragmentDirections
import tatyana.volkova.app.giphy.presentation.gif_list.GifListViewModel

class GifListAdapter : ListAdapter<Gif, GifListAdapter.GifViewHolder>(GifItemDiffCallBack()) {

    var viewModel: GifListViewModel? = null

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

    inner class GifViewHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif) {
            binding.apply {
                gif = item
                viewModel?.let {
                    vm = it
                }
                executePendingBindings()
            }

            binding.ivGif.setOnClickListener {
                it.findNavController().navigate(GifListFragmentDirections.actionGifListFragmentToGifFragment(item))
            }
        }
    }
}