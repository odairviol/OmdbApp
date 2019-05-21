package omdbapp.odair.sdm.ifsp.edu.br.omdbapp

import omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto.DetalheDTO
import omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto.ResultadoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI {

    @GET("/")
    fun getFilmes(@Query("s") titulo: String): Call<ResultadoDTO>

    @GET("/")
    fun getFilme(@Query("i") id: String): Call<DetalheDTO>
}