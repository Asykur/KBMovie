package com.asykurkhamid.kitabisamovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.kitabisamovie.R
import com.asykurkhamid.kitabisamovie.model.ReviewsModel
import kotlinx.android.synthetic.main.item_review.view.*
import java.util.*

class ReviewAdapter(private val reviewList: List<ReviewsModel>) :
    RecyclerView.Adapter<ReviewAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewAdapter.VH(itemView)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(reviewList.get(position))
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val initial = itemView.tvInitial
        private val title = itemView.reviewTitle
        private val subTitle = itemView.reviewSubTitle
        private val desc = itemView.reviewDesc

        fun bindData(reviews: ReviewsModel) {
            initial.text = getInitial(reviews.author)
            title.text = "A review by "+reviews.author
            subTitle.text = "Written by "+reviews.author
            desc.text = reviews.content
        }

        fun getInitial(name: String): String {
            var initial: String = ""
            for (i in name.indices) {
                initial = name[0].toString()
            }
            return initial.toUpperCase(Locale.ROOT)
        }
    }

}