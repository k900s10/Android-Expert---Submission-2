package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.tourismapp.core.ui.TourismAdapter
import com.dicoding.tourismapp.detail.DetailTourismActivity
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val tourismAdapter = TourismAdapter()
        tourismAdapter.onItemClick = { selectedData ->
            val intent = Intent(applicationContext, DetailTourismActivity::class.java)
            intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.getFavoritePariwisata.observe(this) { pariwisata ->
            tourismAdapter.setData(pariwisata)
        }

        binding.favoriteRecyclerView.adapter = tourismAdapter
    }
}