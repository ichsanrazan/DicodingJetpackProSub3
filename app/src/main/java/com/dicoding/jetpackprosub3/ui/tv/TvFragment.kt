package com.dicoding.jetpackprosub3.ui.tv

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpackprosub3.MainActivity
import com.dicoding.jetpackprosub3.R
import com.dicoding.jetpackprosub3.databinding.FragmentTvBinding
import com.dicoding.jetpackprosub3.ui.detail.DetailActivity
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.TV
import com.dicoding.jetpackprosub3.utils.MarginItemDecoration
import com.dicoding.jetpackprosub3.utils.SortUtils.VOTE_BEST
import com.dicoding.jetpackprosub3.viewmodel.ViewModelFactory
import com.dicoding.jetpackprosub3.vo.Status

class TvFragment : Fragment(), TvAdapter.OnItemClickCallback {

    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as MainActivity).setActionBarTitle("TV Shows List")

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvShowAdapter = TvAdapter()
            viewModel.getTvShows(VOTE_BEST).observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            showProgressBar(false)
                            tvShowAdapter.submitList(tvShows.data)
                            tvShowAdapter.setOnItemClickCallback(this)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentTvBinding.rvTv) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.FILM, id)
        intent.putExtra(DetailActivity.CATEGORY, TV)

        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_best -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.sort_worst -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.sort_random -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvBinding.pbTv.isVisible = state
        fragmentTvBinding.rvTv.isInvisible = state
    }

}