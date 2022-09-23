package com.example.pixabayapijusup.Retrofit

import java.net.URI

data class PixabayModel (
    val hits: List<ImageModel>
)

data class ImageModel (
val largeImageURL:String
    )
