package com.dicoding.jetpackprosub3.ui.favorite.tv

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
import com.dicoding.jetpackprosub3.databinding.FragmentTvFavoriteBinding
import com.dicoding.jetpackprosub3.ui.detail.DetailActivity
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.TV
import com.dicoding.jetpackprosub3.utils.MarginItemDecoration
import com.dicoding.jetpackprosub3.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvFavoriteFragment : Fragment(), FavoriteTvAdapter.OnItemClickCallback {

    private var fragTvFavBinding: FragmentTvFavoriteBinding? = null
    private val binding get() = fragTvFavBinding

    private lateinit var favTvViewModel: FavoriteTvViewModel
    private lateinit var favTvAdapter: FavoriteTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragTvFavBinding = FragmentTvFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        favTvViewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                favTvAdapter.submitList(favTvShow)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteTvShow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            favTvViewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

            favTvAdapter = FavoriteTvAdapter()
            favTvAdapter.setOnItemClickCallback(this)

            favTvViewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow != null) {
                    favTvAdapter.submitList(favTvShow)
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(binding?.rvFavoriteTvShow) {
                this?.addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favTvAdapter
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
                val tvShowEntity = favTvAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { favTvViewModel.setFavTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    tvShowEntity?.let { favTvViewModel.setFavTvShow(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.FILM, id)
        intent.putExtra(DetailActivity.CATEGORY, TV)

        context?.startActivity(intent)
    }

}