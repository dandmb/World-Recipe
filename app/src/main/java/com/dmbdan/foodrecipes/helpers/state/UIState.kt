package com.dmbdan.foodrecipes.helpers.state

import com.dmbdan.foodrecipes.domain.model.Result

data class UIState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val recipes : List<Result> = listOf()
)
