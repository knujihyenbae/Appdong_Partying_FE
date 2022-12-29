package com.appdong.parting

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PartyProfileData (
    val name : String,
    val img : Int
) : Parcelable