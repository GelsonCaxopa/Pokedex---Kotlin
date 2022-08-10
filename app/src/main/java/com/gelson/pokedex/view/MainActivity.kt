package com.gelson.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gelson.pokedex.R
import com.gelson.pokedex.api.PokemonRepository
import com.gelson.pokedex.api.model.PokemonResult
import com.gelson.pokedex.domain.Pokemon
import com.gelson.pokedex.domain.PokemonType
import com.gelson.pokedex.viewmodel.PokemonViewModel
import com.gelson.pokedex.viewmodel.PokemonViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    private val viewModel by lazy{
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel :: class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemon)

        viewModel.pokemons.observe(this, androidx.lifecycle.Observer {
            loadRecyclerView(it)
        })

    }


    private fun loadRecyclerView(pokemons : List<Pokemon?>){
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = PokemonAdapter(pokemons)

    }
}