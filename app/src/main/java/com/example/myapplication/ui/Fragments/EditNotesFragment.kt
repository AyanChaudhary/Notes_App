package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date

class EditNotesFragment : Fragment() {

    lateinit var binding: FragmentEditNotesBinding
    val notes by navArgs<EditNotesFragmentArgs>()
    var priority:String?=null
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.etEditTitle.setText(notes.data.title)
        binding.etEditSubtitle.setText(notes.data.subtitle)
        binding.etEditNote.setText(notes.data.notes)
        priority=notes.data.priority
        when(priority){
            "1"->{
                binding.pEditGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pEditRed.setImageResource(0)
                binding.pEditYellow.setImageResource(0)
            }
            "2"->{
                binding.pEditYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pEditRed.setImageResource(0)
                binding.pEditGreen.setImageResource(0)

            }
            "3"->{
                binding.pEditRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pEditGreen.setImageResource(0)
                binding.pEditYellow.setImageResource(0)
            }
        }
        binding.pEditRed.setOnClickListener {
            priority="3"
            binding.pEditRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pEditGreen.setImageResource(0)
            binding.pEditYellow.setImageResource(0)
        }

        binding.pEditYellow.setOnClickListener {
            priority="2"
            binding.pEditYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pEditRed.setImageResource(0)
            binding.pEditGreen.setImageResource(0)

        }

        binding.pEditGreen.setOnClickListener {
            priority="1"
            binding.pEditGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pEditRed.setImageResource(0)
            binding.pEditYellow.setImageResource(0)
        }

        binding.btnEditSave.setOnClickListener {
            updateNotes(it)
        }



        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title=binding.etEditTitle.text.toString()
        val subtitle=binding.etEditSubtitle.text.toString()
        val note=binding.etEditNote.text.toString()
        val sdf=SimpleDateFormat("MMMM d, yyyy")
        val currentDate=sdf.format(Date())
        val Notes=Notes(notes.data.id,title,subtitle,note,currentDate,priority!!)
        viewModel.updateNotes(Notes)
        Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
        getActivity()?.onBackPressed()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu_option,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete_icon){
            val bottomSheet=BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)
            val tv_yes=bottomSheet.findViewById<TextView>(R.id.tv_yes)
            val tv_no=bottomSheet.findViewById<TextView>(R.id.tv_no)
            tv_yes?.setOnClickListener {
                viewModel.delNotes(notes.data.id!!)
                bottomSheet.dismiss()
                getActivity()?.onBackPressed()

            }
            tv_no?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }

        return super.onOptionsItemSelected(item)
    }


}