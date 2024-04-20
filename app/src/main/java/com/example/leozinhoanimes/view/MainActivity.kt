package com.example.leozinhoanimes.view

import AnimesAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.leozinhoanimes.databinding.ActivityMainBinding
import com.example.leozinhoanimes.util.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val retrofit by lazy { RetrofitHelper.jikanApi }
    private lateinit var adapter: AnimesAdapter
    private var gridLayoutManager: GridLayoutManager? = null
    private var paginaAtual = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        getTopAnime()

        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val searchQuery = s.toString().trim()
                adapter.filter(searchQuery)
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = AnimesAdapter { anime ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("title", anime.title)
            intent.putExtra("image", anime.images.jpg.large_image_url)
            intent.putExtra("episodes", anime.episodes)
            intent.putExtra("rating", anime.rating)
            intent.putExtra("score", anime.score)
            intent.putExtra("year", anime.year)
            intent.putExtra("synopsis", anime.synopsis)


            startActivity(intent)
        }
        binding.rvAnimes.adapter = adapter
        binding.rvAnimes.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(this, 3)
        binding.rvAnimes.layoutManager = gridLayoutManager
        binding.rvAnimes.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val canScrollVertically = recyclerView.canScrollVertically(1)
                if (!canScrollVertically) {
                    getTopAnimeNextPage()
                }
            }
        })
    }

    private fun getTopAnimeNextPage() {
        if (paginaAtual < 27000) {
            paginaAtual++
            getTopAnime(paginaAtual)
        }
    }

    private fun getTopAnime(paginaAtual: Int = 1) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofit.getTopAnime(paginaAtual)
                if (response.isSuccessful) {
                    val dadosResposta = response.body()
                    val dadosAnimes = dadosResposta?.DateAnimes
                    withContext(Dispatchers.Main) {
                        dadosAnimes?.let {
                            adapter.addList(it)
                        }
                    }
                } else {
                    Log.i("DADOS", "erro ao recuperar a lista ${response.code()}")
                }
            } catch (e: Exception) {
                Log.i("DADOS", "erro ao fazer a requisição: ${e.message}")
            }
        }
    }
}
