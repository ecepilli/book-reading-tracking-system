package com.example.reading_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reading_tracker.databinding.MylibrarybookItemBinding

class MyLibraryAdapter (
    private val fragmentView: View,
    val clickListener: (userLibraryId: Long) -> Unit,
) : ListAdapter<Userlibrary, MyLibraryAdapter.MyLibraryViewHolder>(MyLibraryDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MyLibraryViewHolder = MyLibraryViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MyLibraryViewHolder, position: Int ) {
        val item = getItem(position)
        holder.bind(item, clickListener,fragmentView)

    }

    class MyLibraryViewHolder(val binding: MylibrarybookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup):MyLibraryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MylibrarybookItemBinding.inflate(layoutInflater, parent, false)
                return MyLibraryViewHolder(binding)
            }
        }

        fun bind(item: Userlibrary, clickListener: (userLibraryId: Long) -> Unit,fragmentView: View) {
            binding.myBooks= item
            binding.root.setOnClickListener { clickListener(item.userLibraryId) }
            Glide.with(fragmentView).load(item.libraryBookPhoto).centerCrop().into(binding.imageView)
        }

    }
}