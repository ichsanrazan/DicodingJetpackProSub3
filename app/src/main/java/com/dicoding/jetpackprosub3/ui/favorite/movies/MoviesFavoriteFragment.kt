package com.dicoding.jetpackprosub3.ui.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpackprosub3.R
import com.dicoding.jetpackprosub3.databinding.FragmentMoviesFavoriteBinding
import com.dicoding.jetpackprosub3.ui.detail.DetailActivity
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.jetpackprosub3.utils.MarginItemDecoration
import com.dicoding.jetpackprosub3.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MoviesFavoriteFragment : Fragment(), FavoriteMoviesAdapter.OnItemClickCallback {

    private var fragMoviesFavoriteBinding: FragmentMoviesFavoriteBinding? = null
    private val binding get() = fragMoviesFavoriteBinding

    private lateinit var favMoviesViewModel: FavoriteMoviesViewModel
    private lateinit var favMoviesadapter: FavoriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragMoviesFavoriteBinding = FragmentMoviesFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        favMoviesViewModel.getFavMovies().observe(viewLifecycleOwner, { favMovies ->
            if (favMovies != null) {
                favMoviesadapter.submitList(favMovies)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteMovies)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            favMoviesViewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]

            favMoviesadapter = FavoriteMoviesAdapter()
            favMoviesadapter.setOnItemClickCallback(this)

            favMoviesViewModel.getFavMovies().observe(viewLifecycleOwner, { favMovies ->
                if (favMovies != null) {
                    favMoviesadapter.submitList(favMovies)
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(binding?.rvFavoriteMovies) {
                this?.addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favMoviesadapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favMoviesadapter.getSwipedData(swipedPosition)
                movieEntity?.let { favMoviesViewModel.setFavMovie(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    movieEntity?.let { favMoviesViewModel.setFavMovie(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        fragMoviesFavoriteBinding = null
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.FILM, id)
        intent.putExtra(DetailActivity.CATEGORY, MOVIE)

        context?.startActivity(intent)
    }
}