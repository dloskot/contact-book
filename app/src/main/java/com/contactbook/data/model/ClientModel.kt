package com.contactbook.data.model

import android.media.Image
import java.util.Date

data class ClientModel(
    var weight: Int = 0,
    var dateOfBirth: Date = Date(),
    var photo: Image? = null
)