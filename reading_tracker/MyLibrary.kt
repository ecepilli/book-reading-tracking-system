package com.example.reading_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.reading_tracker.databinding.FragmentLibraryBinding
import com.example.reading_tracker.databinding.FragmentMyLibraryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyLibrary : Fragment() {
    private var _binding: FragmentMyLibraryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMyLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NewLibraryDatabase.getInstance(application).libraryDao
        val viewModelFactory = MyLibraryViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MyLibraryViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = MyLibraryAdapter(view){ id ->
            viewModel.onBookClicked(id)
        }
        binding.myBooksList.adapter = adapter


        viewModel.myBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToBook.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
                val action =MyLibraryDirections.actionMyLibraryToBookUpdate2(id)
                this.findNavController().navigate(action)
                viewModel.onBookNavigated()
            }
        })

      binding.fab.setOnClickListener{
          view.findNavController()
              .navigate(R.id.action_myLibrary_to_library)
      }


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

