package com.greet.special.model

import com.google.gson.annotations.SerializedName

class ContentListModel(@SerializedName("ContentId") var contentId: Int, @SerializedName("ContentValue") var contentValue: String)