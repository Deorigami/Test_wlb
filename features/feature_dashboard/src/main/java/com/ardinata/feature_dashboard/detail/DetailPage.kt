package com.ardinata.feature_dashboard.detail

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageDrinkDetailBinding
import com.ardinata.feature_dashboard.detail.presenter.GameDetailViewModel
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.molecule.IngredientItem
import com.ardinata.test.test_goplay.util.customSetImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPage(
    override val layout: Int = R.layout.page_drink_detail
) : BaseViewBindingFragment<PageDrinkDetailBinding>() {

    private val viewModel by viewModels<GameDetailViewModel>()

    override fun initBinding(view: View) {
        binding = PageDrinkDetailBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        initView()
    }

    private fun initView() {
        binding?.simpleHeader?.apply {
            onBackPressed = { activity?.onBackPressed() }
            onFavButtonChangeListener = { isFav ->

            }
        }
        getMovieItem()?.let {
            binding?.poster?.customSetImage(it.backdropPath)
            binding?.description?.text = it.overview
            binding?.gameTitle?.text = it.title
            viewModel.getMovieCast.getData(
                it.id.toString(),
                onSuccess = {
                    binding?.ingredientList?.apply {
                        items = it.cast.filter { it.knownForDepartment == "Acting" }.sortedByDescending { it.popularity }.map { IngredientItem.Data(
                            image = it.profilePath,
                            title = it.name,
                            measure = it.character
                        ) }.take(8).toMutableList()
                    }
                }
            )
        }
        getTVItem()?.let {
            binding?.poster?.customSetImage(it.backdropPath)
            binding?.description?.text = it.overview
            binding?.gameTitle?.text = it.name
            viewModel.getTvCast.getData(
                it.id.toString(),
                onSuccess = {
                    binding?.ingredientList?.apply {
                        items = it.cast.filter { it.knownForDepartment == "Acting" }.sortedByDescending { it.popularity }.map { IngredientItem.Data(
                            image = it.profilePath,
                            title = it.name,
                            measure = it.character
                        ) }.take(8).toMutableList()
                    }
                }
            )
        }
    }

    private fun setObserver() {
        viewModel.run {

        }
    }

    private fun getMovieItem() : MovieListItemEntity? = arguments?.getParcelable(MOVIE_ITEM)
    private fun getTVItem() : TVListItemEntity? = arguments?.getParcelable(TV_ITEM)

    companion object {
        const val MOVIE_ITEM = "MOVIE_ITEM"
        const val TV_ITEM = "TV_ITEM"
        const val IS_FAV_RESULT = "IS_FAV_RESULT"
    }
}