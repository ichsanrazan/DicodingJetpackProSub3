package com.dicoding.jetpackprosub3.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpackprosub3.MainActivity
import com.dicoding.jetpackprosub3.R
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.databinding.FragmentMoviesBinding
import com.dicoding.jetpackprosub3.ui.detail.DetailActivity
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.jetpackprosub3.utils.MarginItemDecoration
import com.dicoding.jetpackprosub3.utils.SortUtils.RANDOM
import com.dicoding.jetpackprosub3.utils.SortUtils.VOTE_BEST
import com.dicoding.jetpackprosub3.utils.SortUtils.VOTE_WORST
import com.dicoding.jetpackprosub3.viewmodel.ViewModelFactory
import com.dicoding.jetpackprosub3.vo.Resource
import com.dicoding.jetpackprosub3.vo.Status

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as MainActivity).setActionBarTitle("Movies List")

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            moviesViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            moviesAdapter = MoviesAdapter()
            moviesViewModel.getMovies(VOTE_BEST).observe(viewLifecycleOwner, movieObserver)

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentMoviesBinding.rvMovies) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = moviesAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MoviesEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.setOnItemClickCallback(this)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.FILM, id)
        intent.putExtra(DetailActivity.CATEGORY, MOVIE)

        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.sort_best -> sort = VOTE_BEST
            R.id.sort_worst -> sort = VOTE_WORST
            R.id.sort_random -> sort = RANDOM
        }

        moviesViewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMoviesBinding.pbarMovies.isVisible = state
        fragmentMoviesBinding.rvMovies.isInvisible = state
    }

}