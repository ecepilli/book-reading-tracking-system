package com.example.reading_tracker

import androidx.recyclerview.widget.DiffUtil

class BookDiffItemCallback  : DiffUtil.ItemCallback<Books>() {
    override fun areItemsTheSame(oldItem: Books, newItem: Books)
            = (oldItem.bookId == newItem.bookId)
    override fun areContentsTheSame(oldItem: Books, newItem: Books) = (oldItem == newItem)
}