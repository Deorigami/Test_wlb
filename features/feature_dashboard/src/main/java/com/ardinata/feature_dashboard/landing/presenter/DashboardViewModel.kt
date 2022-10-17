package com.ardinata.feature_dashboard.landing.presenter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ardinata.feature_dashboard.landing.pager.PagerGameList.Companion.FilteredListDataSource.GENERAL
import com.ardinata.feature_dashboard.landing.pager.PagerGameList.Companion.FilteredListDataSource.SEARCH
import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.NonNullMutableLiveData
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.entity.FilterEntity
import com.ardinata.service_cocktail.domain.resource.FilterType
import com.ardinata.service_cocktail.domain.usecase.*
import com.ardinata.service_cocktail.domain.usecase.db.GetFavoriteDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getCategoriesFilterUseCase: GetCategoriesFilterUseCase,
    glassesFilterUseCase: GetGlassesFilterUseCase,
    alcoholicFilterUseCase: GetAlcoholicFilterUseCase,
    getCocktailListUseCase: GetCocktailListUseCase,
    searchCocktailByNameUseCase: SearchCocktailByNameUseCase,
    getFavoriteDrinksUseCase: GetFavoriteDrinksUseCase
) : BaseViewModel() {
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf(categoriesFilter, cocktailList)
    }

    private val alphabet = "abcdefghijklmnopqrstuvwxyz".toList().map { it.toString() }
    private var paginationCount = 0

    val categoriesFilter = StatefulLiveData(
        getCategoriesFilterUseCase,
        viewModelScope
    )

    val glassesFilter = StatefulLiveData(
        glassesFilterUseCase,
        viewModelScope
    )

    val alcoholicFilter = StatefulLiveData(
        alcoholicFilterUseCase,
        viewModelScope
    )

    val cocktailList = StatefulLiveData(
        getCocktailListUseCase,
        viewModelScope
    )

    val searchCocktail = StatefulLiveData(
        searchCocktailByNameUseCase,
        viewModelScope
    )

    val favoriteDrink = StatefulLiveData(
        getFavoriteDrinksUseCase,
        viewModelScope
    )

    val categoriesSelectedFilter = NonNullMutableLiveData(listOf<String>())
    val glassesSelectedFilter = NonNullMutableLiveData(listOf<String>())
    val alcoholicSelectedFilter = NonNullMutableLiveData(listOf<String>())
    val nameFilter = NonNullMutableLiveData("")

    val shownListDataSource = NonNullMutableLiveData(GENERAL)

    private val paginatedCocktailList = MediatorLiveData<List<CocktailDrinkItemEntity>>().apply {
        fun update() {
            val sourceValue = cocktailList.onSuccess.value ?: emptyList()
            val newValue = object : ArrayList<CocktailDrinkItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(sourceValue)
                }
            }

            value = newValue.distinctBy { it.drinkId }
        }

        addSource(cocktailList.onSuccess) { update() }
    }

    val filterData = MediatorLiveData<List<FilterEntity>>().apply {
        fun update() {
            val glassesFilter = glassesFilter.onSuccess.value
            val alcoholicFilter = alcoholicFilter.onSuccess.value
            val categoriesFilter = categoriesFilter.onSuccess.value

            val newValue = object : ArrayList<FilterEntity>() {
                init {
                    glassesFilter?.let { add(it) }
                    alcoholicFilter?.let { add(it) }
                    categoriesFilter?.let { add(it) }
                }
            }

            value = newValue.sortedBy { it.index }
        }
        addSource(glassesFilter.onSuccess) { update() }
        addSource(alcoholicFilter.onSuccess) { update() }
        addSource(categoriesFilter.onSuccess) { update() }
    }

    val filteredCocktailList = MediatorLiveData<List<CocktailDrinkItemEntity>>().apply {
        fun update() {
            val list: MutableList<CocktailDrinkItemEntity> = when(shownListDataSource.value){
                SEARCH -> searchCocktail.onSuccess.value?.toMutableList() ?: mutableListOf()
                GENERAL -> paginatedCocktailList.value?.toMutableList() ?: mutableListOf()
            }
            val newValue = object : ArrayList<CocktailDrinkItemEntity>() {
                init {
                    if (categoriesSelectedFilter.value.isNotEmpty()) list.removeAll {
                        !categoriesSelectedFilter.value.contains(
                            it.category
                        )
                    }
                    if (glassesSelectedFilter.value.isNotEmpty()) list.removeAll {
                        !glassesSelectedFilter.value.contains(
                            it.glass
                        )
                    }
                    if (alcoholicSelectedFilter.value.isNotEmpty()) list.removeAll {
                        !alcoholicSelectedFilter.value.contains(
                            it.alcoholic
                        )
                    }
                    if (nameFilter.value.isNotEmpty()) list.removeAll { !it.drink.lowercase().contains(nameFilter.value.lowercase()) }
                    addAll(list)
                }
            }
            value = newValue
        }
        addSource(categoriesSelectedFilter) { update() }
        addSource(glassesSelectedFilter) { update() }
        addSource(alcoholicSelectedFilter) { update() }
        addSource(nameFilter) { update() }

        addSource(shownListDataSource){update()}
        addSource(paginatedCocktailList) { update() }
        addSource(searchCocktail.onSuccess){ update() }
    }

    init {
        categoriesFilter.getData(Unit)
        alcoholicFilter.getData(Unit)
        glassesFilter.getData(Unit)
        getPaginatedCocktailList()
    }


    fun updateFilter(filterType: FilterType, filter: String) {
        when (filterType) {
            FilterType.Category -> {
                val newValue = if (categoriesSelectedFilter.value.contains(filter)) {
                    categoriesSelectedFilter.value.minus(filter)
                } else {
                    categoriesSelectedFilter.value.plus(filter)
                }
                categoriesSelectedFilter.postValue(newValue)
            }
            FilterType.Glass -> {
                val newValue = if (glassesSelectedFilter.value.contains(filter)) {
                    glassesSelectedFilter.value.minus(filter)
                } else {
                    glassesSelectedFilter.value.plus(filter)
                }
                glassesSelectedFilter.postValue(newValue)
            }
            FilterType.Alcoholic -> {
                val newValue = if (alcoholicSelectedFilter.value.contains(filter)) {
                    alcoholicSelectedFilter.value.minus(filter)
                } else {
                    alcoholicSelectedFilter.value.plus(filter)
                }
                alcoholicSelectedFilter.postValue(newValue)
            }
        }
    }

    fun getSelectedFilterList() = categoriesSelectedFilter.value + glassesSelectedFilter.value + alcoholicSelectedFilter.value

    fun getPaginatedCocktailList() {
        if (
            paginationCount <= alphabet.lastIndex
            && !hasFilter()
            && shownListDataSource.value == GENERAL
        ) {
            cocktailList.getData(
                alphabet[paginationCount],
                onComplete = {
                    paginationCount++
                }
            )
        }
    }

    fun searchCocktail(key: String) {
        searchCocktail.getData(
            key.replace(" ", "_"),
            onSuccess = { clearFilter() }
        )
    }

    private fun clearFilter(){
        nameFilter.postValue("")
        alcoholicSelectedFilter.postValue(emptyList())
        categoriesSelectedFilter.postValue(emptyList())
        glassesSelectedFilter.postValue(emptyList())
    }

    fun hasFilter() = getSelectedFilterList().isNotEmpty() || nameFilter.value.isNotEmpty()
}