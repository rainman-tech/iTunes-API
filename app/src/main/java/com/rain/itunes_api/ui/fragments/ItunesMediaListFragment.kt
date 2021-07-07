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
import com.rain.itunes_api.ui.viewmodels.VisitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_list.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ItunesMediaListFragment : Fragment(R.layout.fragment_media_list) {

    private val viewModel: ItunesMediaViewModel by viewModels()
    private val visitViewModel: VisitViewModel by viewModels()

    @Inject
    lateinit var itunesMediaListAdapter: ItunesMediaListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        subscribeToObservers()

        itunesMediaListAdapter.setItemClickListener {
            //Pass parcelable bundle to another fragment
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
        //Observe data from api request
        viewModel.itunesMediaItems.observe(viewLifecycleOwner) { result ->
            itunesMediaListAdapter.itunesMediaList = result
        }
        //Observe data stored in room database
        visitViewModel.visitsSortedByDate.observe(viewLifecycleOwner) {
            if (it.size <= 1) {
                tv_lastVisit.text = "This is your first visit."
            } else {
                val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                val netDate = Date(it[it.size-2].timestamp)
                tv_lastVisit.text = "Last visit: ${sdf.format(netDate)}"
            }
        }
    }

}