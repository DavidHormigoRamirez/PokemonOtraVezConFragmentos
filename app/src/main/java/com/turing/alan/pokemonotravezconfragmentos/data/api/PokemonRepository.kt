package com.turing.alan.pokemonotravezconfragmentos.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/")
    suspend fun getAllPokemon():PokemonListResponse
    @GET("api/v2/pokemon/{name}/")
    suspend fun fetchPokemon(@Path("name") id:String):PokemonApiModel
}


class PokemonRepository private constructor(private val api:PokemonApi) {

    private val _pokemon = MutableLiveData<List<PokemonApiModel>>()
    val pokemon: LiveData<List<PokemonApiModel>>
        get() = _pokemon

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    suspend fun fetch() {
        val pokemonListResponse = api.getAllPokemon()
        Log.d("DAVID",pokemonListResponse.toString())
        val pokemonList: List<PokemonApiModel> = pokemonListResponse.results.map {
            val pokemonDetail = api.fetchPokemon(it.name)
            pokemonDetail
            /*PokemonApiModel(pokemonDetail.id,
                pokemonDetail.name,
                pokemonDetail.weight,
                pokemonDetail.height,
                pokemonDetail.sprites)*/
        }
        Log.d("DAVID",pokemonList.toString())
        _pokemon.value = pokemonList
    }


}