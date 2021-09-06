package com.dicoding.jetpackprosub3.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.jetpackprosub3.MainActivity
import com.dicoding.jetpackprosub3.R
import com.dicoding.jetpackprosub3.ViewPagerAdapter
import com.dicoding.jetpackprosub3.databinding.FragmentFavoriteBinding
import com.dicoding.jetpackprosub3.ui.favorite.movies.MoviesFavoriteFragment
import com.dicoding.jetpackprosub3.ui.favorite.tv.TvFavoriteFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private var favoriteFragmentBinding: FragmentFavoriteBinding? = null
    private val binding get() = favoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as MainActivity).setActionBarTitle("Favorite")

            setViewPager()
        }
    }

    private fun setViewPager() {
        val fragmentList = listOf(MoviesFavoriteFragment(), TvFavoriteFragment())
        val tabTitle = listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        binding?.viewpager?.adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout2, viewpager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        favoriteFragmentBinding = null
    }
}