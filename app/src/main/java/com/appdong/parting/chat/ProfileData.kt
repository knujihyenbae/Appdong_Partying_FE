package com.appdong.parting.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileData (
    val name : String,
    val age : String,
    val img : Int
) : Parcelable