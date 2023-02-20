package com.contactbook.data.model

import android.media.Image
import java.util.Date

data class ClientModel(
    val weight: Int = 0,
    val dateOfBirth: Date = Date(),
    val photo: Image? = null
)