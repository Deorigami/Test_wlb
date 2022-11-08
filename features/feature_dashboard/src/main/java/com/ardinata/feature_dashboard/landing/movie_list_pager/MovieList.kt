package com.ardinata.feature_dashboard.landing.movie_list_pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerDrinkListBinding
import com.ardinata.feature_dashboard.landing.movie_list_pager.adapter.MovieListVPAdapter
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.organism.TabsCustomItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieList(
    private val pageMode: PageMode,
    override val layout: Int = R.layout.pager_drink_list
) : BaseViewBindingFragment<PagerDrinkListBinding>() {

    private val viewModel by activityViewModels<DashboardViewModel>()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    lateinit var adapter : MovieListVPAdapter
    lateinit var sections : List<MovieDBSection>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sections = MovieDBSection
            .values()
            .filter {
                it.name
                    .lowercase()
                    .contains(if (pageMode == PageMode.MOVIE) "movie" else "tv")
            }
        val sectionFragments = sections.map { data ->
            MovieListPager.createInstance(data)
        }
        adapter = MovieListVPAdapter(
            childFragmentManager,
            lifecycle,
            sectionFragments
        )
    }

    override fun initBinding(view: View) {
        binding = PagerDrinkListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        initObserver()
        binding?.categorySelector?.apply {
            items = sections.mapIndexed { index, moviesection ->
                TabsCustomItem.Data(
                    moviesection.text,
                    index == 0
                )
            }.toMutableList()
        }
        initView()
        setListener()
    }

    private fun setListener() {
        binding?.categorySelector?.apply {
            onPressed = {
                binding?.viewPager?.currentItem = it
            }
        }
    }

    private fun initView() {
        binding?.viewPager?.apply {
            adapter = this@MovieList.adapter
            currentItem = 0
            isUserInputEnabled = false
        }
    }

    private fun initObserver() {

    }

    companion object {
        enum class PageMode{ MOVIE, TV }
    }
}