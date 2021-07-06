package tatyana.volkova.app.giphy.app.presentation.gif_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tatyana.volkova.app.giphy.R

@AndroidEntryPoint
class GifListFragment : Fragment(R.layout.fragment_gif_list) {

    private val viewModel: GifListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner) {
            Log.e(TAG, it.toString())
        }
    }

    companion object{
        const val TAG = "GifListFragment"
    }
}