package com.example.reading_tracker

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.reading_tracker.databinding.FragmentLibraryBinding

/**
 * A simple [Fragment] subclass.
 * Use the [library.newInstance] factory method to
 * create an instance of this fragment.
 */
class library : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NewLibraryDatabase.getInstance(application).bookDao
        val viewModelFactory = BookViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(BookViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = BookItemAdapter(view){ bookId ->
            viewModel.onBookClicked(bookId)
        }
        binding.booksList.adapter = adapter


        viewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToBook.observe(viewLifecycleOwner, Observer { bookId ->
            bookId?.let {
                val action =libraryDirections.actionLibraryToAddMyLibrary2(bookId)
                this.findNavController().navigate(action)
                viewModel.onBookNavigated()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }


