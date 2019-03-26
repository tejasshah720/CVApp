package com.my.cvapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillsItem(
	val keywords: List<String?>? = null,
	val level: String? = null,
	val name: String? = null
) : Parcelable
