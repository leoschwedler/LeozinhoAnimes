package com.example.leozinhoanimes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leozinhoanimes.databinding.ActivityDetalhesBinding
import com.squareup.picasso.Picasso

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetalhesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        val title = bundle?.getString("title")
        val image = bundle?.getString("image")
        val episodes = bundle?.getInt("episodes")
        val rating = bundle?.getString("rating")
        val score = bundle?.getDouble("score")
        val year = bundle?.getInt("year")
        val synopsis = bundle?.getString("synopsis")
        binding.textSeasons.text = year.toString()
        binding.textDescription.text = synopsis
        binding.textScore.text = score.toString()
        binding.textEpisodios.text = episodes.toString()
        binding.textRating.text = rating
        binding.textFilmeTitulo.text = title
        Picasso.get().load(image)
            .into(binding.imgPoster)

    }
}