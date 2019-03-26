package com.my.cvapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.cvapp.R
import com.my.cvapp.contractor.MainContract
import com.my.cvapp.model.CVDetail

/**
 * Created by Tejas Shah on 25/03/19.
 */
class CVAdapter(cvList: List<CVDetail>,listener: MainContract.RecyclerViewClickListener) : RecyclerView.Adapter<CVAdapter.CVViewHolder>() {
    private val cvs = mutableListOf<CVDetail>()
    private val FADE_DURATION = 1000L
    private var lastPosition = -1
    private var mListener: MainContract.RecyclerViewClickListener? = null

    init {
        cvs.clear()
        cvs.addAll(cvList)
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CVViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_candidate_info, parent, false)
        return CVViewHolder(v);
    }

    override fun getItemCount(): Int {
        return cvs.size
    }

    override fun onBindViewHolder(holder: CVViewHolder, position: Int) {
        holder.bind(cvs[position], mListener!!)
        setFadeAnimation(holder.itemView,position)
    }

    private fun setFadeAnimation(view: View,position: Int) {
        if (position > lastPosition) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = FADE_DURATION
            view.startAnimation(anim)
            lastPosition = position;
        }
    }

    class CVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(cvItem: CVDetail, listener: MainContract.RecyclerViewClickListener) = with(itemView){

            val imgCandidate = itemView.findViewById<ImageView>(R.id.imgCandidate)
            val tvCandidateName = itemView.findViewById<TextView>(R.id.tvCandidateName)
            val tvCandidateDesignation = itemView.findViewById<TextView>(R.id.tvCandidateDesignation)

            Glide.with(imgCandidate.context).load(cvItem.thumbnailpicture).into(imgCandidate)
            tvCandidateName.setText(cvItem.name)
            tvCandidateDesignation.setText(cvItem.label)

            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition, cvItem)
            }
        }
    }
}