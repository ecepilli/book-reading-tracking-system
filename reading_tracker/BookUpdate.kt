package com.example.reading_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.reading_tracker.databinding.FragmentAddMyLibraryBinding
import com.example.reading_tracker.databinding.FragmentBookUpdateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookUpdate.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookUpdate : Fragment() {

    private var _binding: FragmentBookUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentBookUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = BookUpdateArgs.fromBundle(requireArguments()).id
        val application = requireNotNull(this.activity).application
        val dao = NewLibraryDatabase.getInstance(application).libraryDao
        val viewModelFactory = UpdateViewModelFactory(id, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UpdateViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userLibrary.observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(this).load(viewModel.userLibrary.value?.libraryBookPhoto).into(binding.imageView2)
            }
        })
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_bookUpdate2_to_myLibrary)
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