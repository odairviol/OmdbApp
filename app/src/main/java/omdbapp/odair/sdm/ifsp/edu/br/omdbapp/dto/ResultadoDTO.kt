package omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto

import com.google.gson.annotations.SerializedName

class ResultadoDTO {

    @SerializedName("Response")
    val response: String? = null

    @SerializedName("Search")
    val filmes: List<FilmeDTO> = arrayListOf()

    val totalResults: String? = null
}