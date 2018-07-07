package com.greet.special.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ContentListModel(@SerializedName("ContentId") var contentId: Int, @SerializedName("ContentValue") var contentValue: String) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(contentId)
        parcel.writeString(contentValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContentListModel> {
        override fun createFromParcel(parcel: Parcel): ContentListModel {
            return ContentListModel(parcel)
        }

        override fun newArray(size: Int): Array<ContentListModel?> {
            return arrayOfNulls(size)
        }
    }

}