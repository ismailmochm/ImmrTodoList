package com.immr.immrtodolist.ui.home.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.immr.immrtodolist.core.BaseActivity
import com.immr.immrtodolist.data.database.NoteDB
import com.immr.immrtodolist.databinding.ActivityListNoteBinding
import com.immr.immrtodolist.ui.home.adapter.AdapterNotes
import com.immr.immrtodolist.ui.home.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListNoteActivity: BaseActivity() {

    private lateinit var binding: ActivityListNoteBinding

    companion object {
        lateinit var mActivity: ListNoteActivity
    }

    private lateinit var adapterNotes: AdapterNotes

    val db by lazy { NoteDB(this) }

    private val mDataNote = mutableListOf<Note>()

    override fun onStart() {
        super.onStart()
        loadNote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this

        initAction()
        adapterNote()
    }

    private fun loadNote(){
        CoroutineScope(Dispatchers.IO).launch {
            val note = db.noteDao().getNote()
            if(note.isNotEmpty()){
                mDataNote.clear()
                mDataNote.addAll(note)
                withContext(Dispatchers.Main) {
                    adapterNotes.notifyDataSetChanged()
                }
            }
        }
    }

    private fun adapterNote() {
        adapterNotes = AdapterNotes(mDataNote, mActivity)

        binding.rvNotes.apply {
            adapter = adapterNotes
            layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        }

        adapterNotes.onItemClick = { items ->
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Note(items.id, items.title, items.desc, items.image, !items.isChecked)
                )
                loadNote()
            }
        }

        adapterNotes.onItemClickDelete = { items ->
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().deleteNote(
                    Note(items.id, items.title, items.desc, items.image, !items.isChecked)
                )
                loadNote()
            }
        }
    }

    private fun initAction() {
        binding.btnAddNotes.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}