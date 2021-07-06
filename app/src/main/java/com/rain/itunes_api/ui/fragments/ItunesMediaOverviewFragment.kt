package com.rain.itunes_api.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rain.itunes_api.R
import com.rain.itunes_api.domain.model.ItunesMedia
import kotlinx.android.synthetic.main.fragment_media_overview.*

class ItunesMediaOverviewFragment : Fragment(R.layout.fragment_media_overview) {

    private var itunesMedia: ItunesMedia? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<ItunesMedia>("itunesMedia")?.let {
            itunesMedia = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        vv_preview.setVideoPath(itunesMedia?.previewUrl)
        vv_preview.setOnPreparedListener {
            vv_preview.start()
            it.isLooping = true
        }

        tv_trackName.text = itunesMedia?.trackName
        tv_genre.text = itunesMedia?.primaryGenreName
        tv_longDescription.text = itunesMedia?.longDescription
        tv_price.text = "${itunesMedia?.currency} ${itunesMedia?.trackPrice}"
    }

}