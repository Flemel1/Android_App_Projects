package com.example.coronaapp.data.util

import com.example.coronaapp.R

class Contract {

    companion object {
        const val API_URL = "https://api.kawalcorona.com"
        val imageResourceID = arrayOf(R.drawable.man, R.drawable.heartbeat, R.drawable.sick)
        val infoImageResourceID = arrayOf(R.drawable.hand_wash, R.drawable.man, R.drawable.social_distancing)
        val bantuanImageResourceID = arrayOf(R.drawable.phone_call, R.drawable.smartphone)
        val textTitle = arrayOf("Positif","Sembuh", "Meninggal")
    }

}