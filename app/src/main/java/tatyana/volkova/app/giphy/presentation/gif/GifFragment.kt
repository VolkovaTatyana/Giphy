package tatyana.volkova.app.giphy.presentation.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import tatyana.volkova.app.giphy.R
import tatyana.volkova.app.giphy.databinding.FragmentGifBinding
import tatyana.volkova.app.giphy.presentation.adapters.GifAdapter

@AndroidEntryPoint
class GifFragment : Fragment(R.layout.fragment_gif) {

    private lateinit var viewPager: ViewPager2
    private val adapter = GifAdapter()

    private val args: GifFragmentArgs by navArgs()
    private val viewModel: GifViewModel by viewModels()

    lateinit var binding: FragmentGifBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGifBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.setGif(args.gif)
        viewPager = binding.vpGifs
        viewPager.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPair().observe(viewLifecycleOwner) {
            adapter.submitList(it.first)
            viewPager.setCurrentItem(it.second, false)
        }
    }
}