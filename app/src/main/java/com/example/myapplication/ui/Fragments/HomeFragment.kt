package com.example.myapplication.ui.Fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel:NotesViewModel by viewModels()
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.fabAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding.rvHomeNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rvHomeNotes.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvHomeNotes.adapter = NotesAdapter(requireContext(), notesList)
        }

        binding.ivFilterBtn.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner,{notesList->
                binding.rvHomeNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rvHomeNotes.adapter=NotesAdapter(requireContext(),notesList)
            })
        }
        binding.btnHighPriority.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{notesList->
                binding.rvHomeNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rvHomeNotes.adapter=NotesAdapter(requireContext(),notesList)
            })
        }
        binding.btnMediumPriority.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{notesList->
                binding.rvHomeNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rvHomeNotes.adapter=NotesAdapter(requireContext(),notesList)
            })
        }
        binding.btnLowPriority.setOnClickListener{
            viewModel.getLowNotes().observe(viewLifecycleOwner,{notesList->
                binding.rvHomeNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rvHomeNotes.adapter=NotesAdapter(requireContext(),notesList)
            })
        }

        return binding.root
    }



}