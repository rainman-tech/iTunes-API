package com.rain.itunes_api.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rain.itunes_api.R
import com.rain.itunes_api.domain.model.ItunesMedia
import com.rain.itunes_api.ui.viewmodels.ItunesMediaViewModel
import kotlinx.android.synthetic.main.fragment_media_overview.*

class ItunesMediaOverviewFragment : Fragment(R.layout.fragment_media_overview) {

    private val viewModel: ItunesMediaViewModel by activityViewModels()
    private var itunesMedia: ItunesMedia? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Retrieve parcelable bundle from arguments
        arguments?.getParcelable<ItunesMedia>("itunesMedia")?.let {
            itunesMedia = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        if (itunesMedia != null) {
            //Bind data to the view components with kotlin synthetic
            vv_preview.setVideoPath(itunesMedia!!.previewUrl)
            vv_preview.setOnPreparedListener {
                vv_preview.start()
                it.isLooping = true
            }
            tv_trackName.text = itunesMedia!!.trackName
            tv_genre.text = itunesMedia!!.primaryGenreName
            tv_longDescription.text = itunesMedia!!.longDescription
            tv_price.text = "${itunesMedia!!.currency} ${itunesMedia!!.trackPrice}"

            //Set the color of heart depending on the value of isFavorite
            if (itunesMedia!!.isFavorite) {
                iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            //Update value of mutable data in viewmodel and update UI of heart
            iv_favorite.setOnClickListener {
                if (itunesMedia!!.isFavorite) {
                    iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                } else {
                    iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                viewModel.updateItunesMedia(itunesMedia!!)
            }
         }
    }

}