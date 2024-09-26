package com.example.pokemonproject.domain.model

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.pokemonproject.core.utils.serializableType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class PokemonDetails(val detailsScreenModel: DetailsScreenModel) {
    companion object {
        val typeMap = mapOf(typeOf<DetailsScreenModel>() to serializableType<DetailsScreenModel>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<PokemonDetails>(typeMap)
    }
}

@Parcelize
@Serializable
data class DetailsScreenModel(
    val id: String? = null,
    val image: ImageModel? = null,
    val name: String? = null,
    val type: List<String>? = null,
    val subType: List<String>? = null,
    val level: String? = null,
    val hp: String? = null,
    val attacks: List<AttackModel>? = null,
    val weaknesses: List<WeaknessModel>? = null,
    val abilities: List<AbilityModel>? = null,
    val resistances: List<ResistanceModel>? = null
) : Parcelable

@Parcelize
@Serializable
data class ImageModel(
    val small: String?,
    val large: String?
) : Parcelable

@Parcelize
@Serializable
data class AttackModel(
    val name: String? = null,
    val cost: List<String>,
    val convertedEnergyCost: Int? = null,
    val damage: String? = null,
    val text: String? = null
) : Parcelable

@Parcelize
@Serializable
data class WeaknessModel(
    val type: String? = null,
    val value: String? = null
) : Parcelable

@Parcelize
@Serializable
data class AbilityModel(
    val name: String? = null,
    val text: String? = null,
    val type: String? = null
) : Parcelable

@Parcelize
@Serializable
data class ResistanceModel(
    val type: String? = null,
    val value: String? = null
) : Parcelable
