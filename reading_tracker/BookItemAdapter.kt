package com.example.reading_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reading_tracker.databinding.BookItemBinding

class BookItemAdapter(
    private val fragmentView:View,
    val clickListener: (bookId: Long) -> Unit,
    ) :ListAdapter<Books, BookItemAdapter.BookItemViewHolder>(BookDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : BookItemViewHolder = BookItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int, ) {
        val item = getItem(position)
        holder.bind(item, clickListener,fragmentView)

    }

    class BookItemViewHolder(val binding: BookItemBinding, ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): BookItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookItemBinding.inflate(layoutInflater, parent, false)
                return BookItemViewHolder(binding)
            }
        }

        fun bind(item: Books, clickListener: (bookId: Long) -> Unit,fragmentView: View) {
            binding.book = item
            binding.root.setOnClickListener { clickListener(item.bookId) }
            Glide.with(fragmentView).load(item.photo_book).centerCrop().into(binding.imageView)
        }

    }
}