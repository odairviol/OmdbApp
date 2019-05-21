package omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto

import com.google.gson.annotations.SerializedName

data class RatingDTO(

    @SerializedName("Source")
    val source: String?,

    @SerializedName("Value")
    val value: String?
)