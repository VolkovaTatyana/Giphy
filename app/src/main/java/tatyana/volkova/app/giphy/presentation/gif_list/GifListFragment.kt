package tatyana.volkova.app.giphy.presentation.gif_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tatyana.volkova.app.giphy.R
import tatyana.volkova.app.giphy.databinding.FragmentGifListBinding
import tatyana.volkova.app.giphy.presentation.adapters.GifListAdapter
import tatyana.volkova.app.giphy.presentation.hideKeyboard
import tatyana.volkova.app.giphy.presentation.pagination.paging

@AndroidEntryPoint
class GifListFragment : Fragment(R.layout.fragment_gif_list) {

    private val viewModel: GifListViewModel by viewModels()
    lateinit var binding: FragmentGifListBinding

    private val adapter = GifListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGifListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.rvGifList.layoutManager =
            if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(requireContext(), 2)
            } else {
                GridLayoutManager(requireContext(), 3)
            }

        binding.rvGifList.paging {
            viewModel.nextPage()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGifList.adapter = adapter
        adapter.viewModel = viewModel
        viewModel.getList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.searchView.setOnClickListener { binding.searchView.isIconified = false }
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.createObserveRequest(it)
                }
                requireActivity().hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
//                    viewModel.createObserveRequest(it)
                }
                return true
            }
        })
    }

    //For logs
    companion object {
        const val TAG = "GifListFragment"
    }
}