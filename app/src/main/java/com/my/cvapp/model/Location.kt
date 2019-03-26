package com.my.cvapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
	val address: String? = null,
	val city: String? = null,
	val countryCode: String? = null,
	val postalCode: String? = null,
	val region: String? = null
) : Parcelable
