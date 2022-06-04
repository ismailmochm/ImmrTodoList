package com.immr.immrtodolist.ui.home.activity

import android.os.Bundle
import com.immr.immrtodolist.core.BaseActivity
import com.immr.immrtodolist.data.database.NoteDB
import com.immr.immrtodolist.databinding.ActivityAddNoteBinding
import com.immr.immrtodolist.ui.home.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteActivity: BaseActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    companion object {
        lateinit var mActivity: AddNoteActivity
    }

    val db by lazy { NoteDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this

        initAction()
    }

    private fun initAction() {
        binding.btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, binding.inputTitle.text.toString(), binding.inputDesc.text.toString(), "", false)
                )
                finish()
            }
        }
    }
}