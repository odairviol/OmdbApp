package omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto

import com.google.gson.annotations.SerializedName

data class FilmeDTO(

    @SerializedName("Poster")
    val poster: String?,

    @SerializedName("Title")
    val title: String?,

    @SerializedName("Type")
    val type: String?,

    @SerializedName("Year")
    val year: String?,

    val imdbID: String?
)