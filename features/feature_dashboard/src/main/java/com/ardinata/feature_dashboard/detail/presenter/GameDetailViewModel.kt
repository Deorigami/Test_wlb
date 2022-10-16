package com.ardinata.feature_dashboard.detail.presenter

import androidx.lifecycle.viewModelScope
import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import com.eyedea.service_cocktail.domain.usecase.db.DeleteFavDrinkUseCase
import com.eyedea.service_cocktail.domain.usecase.db.GetFavoriteDrinksUseCase
import com.eyedea.service_cocktail.domain.usecase.db.InsertFavDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    insertFavDrinksUseCase: InsertFavDrinksUseCase,
    getFavoriteDrinksUseCase: GetFavoriteDrinksUseCase,
    deleteFavDrinkUseCase: DeleteFavDrinkUseCase
) : BaseViewModel(){
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }

    val insertFavDrink = StatefulLiveData(
        insertFavDrinksUseCase,
        viewModelScope
    )

    val favDrinks = StatefulLiveData(
        getFavoriteDrinksUseCase,
        viewModelScope
    )

    val deleteFavDrinks = StatefulLiveData(
        deleteFavDrinkUseCase,
        viewModelScope
    )

    init {
        favDrinks.execute(Unit)
    }

    fun getFavDrinkIds() = favDrinks.onSuccess.value?.map { it.drinkId } ?: emptyList()
}