package com.dicoding.mybenalien

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Alien(
    val name: String,
    val description: String,
    val photo: Int,
) : Parcelable

