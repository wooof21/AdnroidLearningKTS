package com.adnroidlearningkts.firebase.authentication.model

import com.google.firebase.Timestamp


data class Journal(val title: String? = null,
                    val thoughts: String? = null,
                    val imgUrl: String? = null,
    val userId: String? = null,
    val username: String? = null,
    val timeAdded: Timestamp? = null)
