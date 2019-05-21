package omdbapp.odair.sdm.ifsp.edu.br.omdbapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe.*
import okhttp3.OkHttpClient
import okhttp3.Request
import omdbapp.odair.sdm.ifsp.edu.br.omdbapp.dto.DetalheDTO
import omdbapp.odair.sdm.ifsp.edu.br.omdbapp.util.Constantes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalheActivity : AppCompatActivity() {

    private var id: String = ""

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor { chain ->
            // Resgatando requisição interceptada
            val reqInterceptada: Request = chain.request()
            // Criando nova requisição a partir da interceptada e adicionando campos de cabeçalho
            val url =
                    reqInterceptada.url().newBuilder().addQueryParameter(Constantes.KEY_FIELD, Constantes.KEY_VALUE).build()
            val novaReq: Request = reqInterceptada.newBuilder()
                    .url(url)
                    .method(reqInterceptada.method(), reqInterceptada.body())
                    .build()
            // retornando a nova requisição preenchdia
            chain.proceed(novaReq)
        }
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    private val retrofit: Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constantes.URL_BASE).client(okHttpClientBuilder.build()).build()

    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    private val omdbApi: OmdbAPI = retrofit.create(OmdbAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val heigth = dm.heightPixels

        window.setLayout(((width * .9).toInt()), ((heigth * .8).toInt()))
        this.supportActionBar?.hide()
        id = intent.getStringExtra("id")

        recuperaFilmePorId()
    }

    private fun recuperaFilmePorId() {
        omdbApi.getFilme(id).enqueue(
            object : Callback<DetalheDTO> {
                override fun onFailure(call: Call<DetalheDTO>, t: Throwable) {
                    //mainContent?.snackbar("Erro: " + t.message)
                }

                override fun onResponse(call: Call<DetalheDTO>, response: Response<DetalheDTO>) {
                    val filme = response.body()

                    if (filme != null) {
                        if (filme.poster?.isNotBlank()!!) {
                            Picasso.get().load(filme.poster).into(imageView2)
                        }
                        textViewTitulo.text = filme.title
                        textViewGenero.text = filme.genre
                        textViewAno.text = filme.year
                        textViewAtores.text = filme.actors?.replace(", ",System.getProperty("line.separator"))
                        textViewDiretor.text = filme.director
                        textViewPlot.text = filme.plot
                        textViewRating.text = "Rating: ${filme.imdbRating}"
                    }
                }
            }
        )
    }

    fun fechar(view: View){
        finish()
    }
}
