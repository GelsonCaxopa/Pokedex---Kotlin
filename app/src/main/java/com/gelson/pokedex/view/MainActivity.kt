package com.gelson.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gelson.pokedex.R
import com.gelson.pokedex.api.PokemonRepository
import com.gelson.pokedex.api.model.PokemonResult
import com.gelson.pokedex.domain.Pokemon
import com.gelson.pokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemon)
//        val charmander = Pokemon(
//            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
//            1, "Charmander", listOf(PokemonType("Fire"))
//        )
//        val pokemons = listOf(
//            charmander, charmander, charmander, charmander, charmander, charmander
//        )

        Thread(Runnable {
            loadPokemons()

        }).start()


    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let{

            val pokemons : List<Pokemon?> = it.map {pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/","")
                    .replace("/","").toInt()
                val pokemonApiResult =  PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map{ type ->
                            type.type
                        }
                    )
                }
                }

            val layoutManager = LinearLayoutManager(this)
            recyclerView.post{
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }


        }

    }


}