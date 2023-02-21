package com.contactbook.data.model

import android.net.Uri
import java.time.LocalDate

data class ClientModel(
    var weight: Int = 0,
    var dateOfBirth: LocalDate = LocalDate.now(),
    var photo: Uri? = null,
    var systemOfMeasure: SystemOfMeasure = SystemOfMeasure.IMPERIAL
)