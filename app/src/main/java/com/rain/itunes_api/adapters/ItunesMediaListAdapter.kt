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

//Adapter for recyclerview
class ItunesMediaListAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ItunesMediaListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //List differ is a tool that takes 2 list and calculates the difference between those lists,
    //which enables our recyclerview to update items that are new in the new list.
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

    //Binding data from the object to the viewholders in the recyclerview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itunesMedia = itunesMediaList[position]
        holder.itemView.apply {
            glide.load(itunesMedia.artworkUrl100).into(iv_thumbnail)
            tv_trackName.text = itunesMedia.trackName
            tv_genre.text = itunesMedia.primaryGenreName
            tv_price.text = "${itunesMedia.currency} ${itunesMedia.trackPrice}"

            //Change the heart drawable if itunesMedia is added in favorites
            if (itunesMedia.isFavorite) {
                iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            //Separated on click listener for recyclerview item and addFavorite
            iv_favorite.setOnClickListener {
                onFavoriteClickListener?.let { click ->
                    click(itunesMedia)
                }
            }

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(itunesMedia)
                }
            }
        }
    }

    private var onItemClickListener: ((ItunesMedia) -> Unit)? = null
    private var onFavoriteClickListener: ((ItunesMedia) -> Unit)? = null

    fun setItemClickListener(listener: (ItunesMedia) -> Unit) {
        onItemClickListener = listener
    }

    fun setFavoriteClickListener(listener: (ItunesMedia) -> Unit) {
        onFavoriteClickListener = listener
    }

    override fun getItemCount(): Int {
        return itunesMediaList.size
    }

}