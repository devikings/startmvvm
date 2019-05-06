package com.brunocardoso.startmvvm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.brunocardoso.startmvvm.R
import com.brunocardoso.startmvvm.models.NicePlace
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecyclerAdapter(
    private var mContext: Context,
    private var mNicePlaces: MutableList<NicePlace>): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_place, p0, false);
        val viewHolder = RecyclerViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewHolder, i: Int) {
        val mNicePlace = mNicePlaces.get(i)

        // Set the name of the 'NicePlace'
        viewHolder.mName.setText( mNicePlace.title )

        // Set the image
        val defaultOptions = RequestOptions()
            .error(R.drawable.ic_launcher_background)

        Glide.with(mContext)
            .setDefaultRequestOptions(defaultOptions)
            .load(mNicePlaces.get(i).imageUrl)
            .into(viewHolder.mImage)
    }

    override fun getItemCount(): Int {
        return mNicePlaces.size
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var mName: TextView
        lateinit var mImage: ImageView

        init {
            mName = itemView.findViewById(R.id.tv_name)
            mImage = itemView.findViewById(R.id.civ_photo)
        }
    }

}