package com.example.reading_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.reading_tracker.databinding.FragmentAddMyLibraryBinding
import com.example.reading_tracker.databinding.FragmentLibraryBinding

class AddMyLibrary : Fragment() {
    private var _binding: FragmentAddMyLibraryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentAddMyLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        val bookId = AddMyLibraryArgs.fromBundle(requireArguments()).bookId
        val application = requireNotNull(this.activity).application
        val dao = NewLibraryDatabase.getInstance(application).bookDao
        val libraryDao = NewLibraryDatabase.getInstance(application).libraryDao
        val viewModelFactory = AddMyLibraryViewModelFactory(bookId, dao,libraryDao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddMyLibraryViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.book.observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(this).load(viewModel.book.value?.photo_book).into(binding.imageView2)
            }
        })
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_addMyLibrary2_to_myLibrary)
                viewModel.onNavigatedToList()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}