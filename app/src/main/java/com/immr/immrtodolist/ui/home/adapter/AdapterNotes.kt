package com.immr.immrtodolist.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.immr.immrtodolist.R
import com.immr.immrtodolist.databinding.ItemsListNotesBinding
import com.immr.immrtodolist.ui.home.model.Note

class AdapterNotes(data: List<Note>, context: Context) :
    ListAdapter<Note, NoteViewHolder>(ItemDiffCallback) {
    private val mData: List<Note> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private val mContext: Context = context
    var onItemClick: ((Note) -> Unit)? = null
    var onItemClickDelete: ((Note) -> Unit)? = null

    companion object ItemDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemsListNotesBinding.inflate(mInflater, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(mData[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(mData[position])
        }

        holder.btnDelete.setOnClickListener {
            onItemClickDelete?.invoke(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}

class NoteViewHolder(binding: ItemsListNotesBinding) : RecyclerView.ViewHolder(binding.root) {
    val title = binding.title
    val desc = binding.desc
    val image = binding.image
    val checked = binding.cecked
    val btnDelete = binding.delete

    fun bind(item: Note){
        title.text = item.title
        desc.text = item.desc

        if(item.isChecked){
            checked.setImageResource(R.drawable.ic_radio_active)
        }else{
            checked.setImageResource(R.drawable.ic_radio_pasive)
        }

    }
}