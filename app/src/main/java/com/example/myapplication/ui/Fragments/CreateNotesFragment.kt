package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.ActivityMainBinding.bind
import com.example.myapplication.databinding.ActivityMainBinding.inflate
import com.example.myapplication.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding:FragmentCreateNotesBinding
    var priority="1"
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
        binding.pRed.setOnClickListener {
            priority="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)

        }

        binding.pGreen.setOnClickListener {
            priority="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)

        }
        binding.btnSave.setOnClickListener {
            if(binding.etTitle.text.isEmpty()) Toast.makeText(requireContext(), "Please enter your title", Toast.LENGTH_SHORT).show()
            else createNote(it)
        }
        return binding.root
    }

    private fun createNote(it: View?) {
        val title=binding.etTitle.text
        val subtitle= binding.etSubtitle.text
        val note= binding.etNote.text
        val sdf = SimpleDateFormat("MMMM d, yyyy")
        val currentDate = sdf.format(Date())
        viewModel.addNotes(Notes(null,title.toString(),subtitle.toString(),note.toString(),currentDate,priority))
        Toast.makeText(requireContext(), "Note created Successfully", Toast.LENGTH_SHORT).show()
        getActivity()?.onBackPressed()
//        findNavController().navigate(R.id.action_createNotesFragment_to_homeFragment)

    }

}