package com.my.cvapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CVDetail(
	@SerializedName("summary") var summary: String? = null,
	@SerializedName("skills") var skills: List<SkillsItem?>? = null,
	@SerializedName("website") var website: String? = null,
	@SerializedName("phone") var phone: String? = null,
	@SerializedName("name") var name: String? = null,
	@SerializedName("location") var location: Location? = null,
	@SerializedName("label") var label: String? = null,
	@SerializedName("userid") var userid: Int? = null,
	@SerializedName("picture") var picture: String? = null,
	@SerializedName("email") var email: String? = null,
	@SerializedName("thumbnailpicture") var thumbnailpicture: String? = null
) : Parcelable
