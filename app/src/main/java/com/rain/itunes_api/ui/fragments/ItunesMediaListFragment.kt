package com.rain.itunes_api.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rain.itunes_api.R
import com.rain.itunes_api.adapters.ItunesMediaListAdapter
import com.rain.itunes_api.ui.viewmodels.ItunesMediaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_list.*
import javax.inject.Inject

@AndroidEntryPoint
class ItunesMediaListFragment : Fragment(R.layout.fragment_media_list) {

    private val viewModel: ItunesMediaViewModel by viewModels()

    @Inject
    lateinit var itunesMediaListAdapter: ItunesMediaListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        subscribeToObservers()

        itunesMediaListAdapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putParcelable("itunesMedia", it)
            findNavController().navigate(R.id.viewItunesMedia, bundle)
        }
    }

    private fun setUpRecyclerView() = rv_itunesMediaList.apply {
        adapter = itunesMediaListAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        viewModel.itunesMediaItems.observe(viewLifecycleOwner) { result ->
            itunesMediaListAdapter.itunesMediaList = result
        }
    }

}