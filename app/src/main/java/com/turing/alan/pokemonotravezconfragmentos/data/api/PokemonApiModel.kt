package com.turing.alan.pokemonotravezconfragmentos.data.api

import com.google.gson.annotations.SerializedName

data class PokemonApiModel(
    val id:Int,
    val name:String,
    val weight:Int,
    val height:Int,
    val sprites:Sprites
)
data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String,
    val other: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDetail
)

data class OfficialArtworkDetail(
    @SerializedName("front_default")
    val frontDefault: String
)
data class PokemonListItemResponse(
    val name: String,
    val url: String
)
data class PokemonListResponse(
    val results:List<PokemonListItemResponse>
)
