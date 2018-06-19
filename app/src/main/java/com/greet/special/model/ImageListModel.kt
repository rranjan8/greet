package com.greet.special.model

import com.google.gson.annotations.SerializedName

class ImageListModel(@SerializedName("ImageId") var imageId: Int, @SerializedName("ImageUrl") var imageUrl: String)