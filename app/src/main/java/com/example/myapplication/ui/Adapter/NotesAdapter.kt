package com.example.myapplication.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.databinding.RvItemViewBinding
import com.example.myapplication.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding:RvItemViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NotesViewHolder {
       return NotesViewHolder(RvItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val item=notesList[position]
        holder.binding.tvNotesTitle.text=item.title
        holder.binding.tvNotesSubtitle.text=item.subtitle
        holder.binding.tvNotesDate.text=item.date
        when(item.priority){
            "1"->{
                holder.binding.notesPriority.setImageResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.notesPriority.setImageResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.notesPriority.setImageResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(item)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int=notesList.size
}