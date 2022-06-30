package com.example.reading_tracker

import androidx.recyclerview.widget.DiffUtil

class MyLibraryDiffItemCallback : DiffUtil.ItemCallback<Userlibrary>() {
        override fun areItemsTheSame(oldItem: Userlibrary, newItem:Userlibrary)
                = (oldItem.userLibraryId== newItem.userLibraryId)
        override fun areContentsTheSame(oldItem: Userlibrary, newItem: Userlibrary) = (oldItem == newItem)
    }