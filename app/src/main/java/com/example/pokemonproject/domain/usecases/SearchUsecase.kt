package com.example.pokemonproject.domain.usecases

import com.example.pokemonproject.domain.model.DetailsScreenModel
import javax.inject.Inject

class SearchUsecase @Inject constructor() {

    operator fun invoke(name: String, list: List<DetailsScreenModel>): List<DetailsScreenModel> {
        if (name.isBlank()) {
            return list
        }

        return list.filter {
            it.name.orEmpty().lowercase().contains(name.lowercase())
        }
    }
}