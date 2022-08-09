package com.gelson.pokedex.domain

data class Pokemon(

    val number: Int,
    val name: String,
    val types: List<PokemonType>

){
    val formattedName = name.capitalize()
    val formattedNumber = number.toString().padStart(3, '0')
    val imagUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"

}

