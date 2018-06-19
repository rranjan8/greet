package com.greet.special.model

import com.google.gson.annotations.SerializedName

class ContentAndImageListModel(@SerializedName("contentArray") var contentList:MutableList<ContentListModel>,
                               @SerializedName("imageArray") var imageList:MutableList<ImageListModel>)