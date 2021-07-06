package com.rain.itunes_api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rain.itunes_api.R
import com.rain.itunes_api.domain.model.ItunesMedia
import kotlinx.android.synthetic.main.item_itunes_media.view.*
import kotlinx.android.synthetic.main.item_itunes_media.view.tv_trackName
import kotlinx.android.synthetic.main.item_itunes_media.view.tv_genre
import kotlinx.android.synthetic.main.item_itunes_media.view.tv_price
import javax.inject.Inject

class ItunesMediaListAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ItunesMediaListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<ItunesMedia>() {
        override fun areItemsTheSame(oldItem: ItunesMedia, newItem: ItunesMedia): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: ItunesMedia, newItem: ItunesMedia): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var itunesMediaList: List<ItunesMedia>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_itunes_media, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itunesMedia = itunesMediaList[position]
        holder.itemView.apply {
            glide.load(itunesMedia.artworkUrl100).into(iv_thumbnail)
            tv_trackName.text = itunesMedia.trackName
            tv_genre.text = itunesMedia.primaryGenreName
            tv_price.text = "${itunesMedia.currency} ${itunesMedia.trackPrice}"

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(itunesMedia)
                }
            }
        }
    }

    private var onItemClickListener: ((ItunesMedia) -> Unit)? = null

    fun setItemClickListener(listener: (ItunesMedia) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return itunesMediaList.size
    }

}