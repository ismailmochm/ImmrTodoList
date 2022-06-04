package com.immr.immrtodolist.ui.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(true)
    val id: Int,
    val title: String,
    val desc: String,
    val image: String,
    var isChecked: Boolean,
        )