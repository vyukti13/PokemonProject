package com.example.pokemonproject.data.model

import android.os.Parcelable
import com.example.pokemonproject.domain.model.AbilityModel
import com.example.pokemonproject.domain.model.AttackModel
import com.example.pokemonproject.domain.model.DetailsScreenModel
import com.example.pokemonproject.domain.model.ImageModel
import com.example.pokemonproject.domain.model.ResistanceModel
import com.example.pokemonproject.domain.model.WeaknessModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDto(
    @IgnoredOnParcel
    @SerializedName("data")
    val data: List<DetailsScreenDto>? = null
) : Parcelable


@Parcelize
data class DetailsScreenDto(
    val id: String? = null,
    val images: ImageDto? = null,
    val name: String? = null,
    val type: List<String>? = null,
    val subtypes: List<String>? = null,
    val level: String? = null,
    val hp: String? = null,
    val attacks: List<AttackDto>? = null,
    val weaknesses: List<WeaknessDto>? = null,
    val abilities: List<AbilityDto>? = null,
    val resistances: List<ResistanceDto>? = null
) : Parcelable {
    fun toDetailsScreenModel(): DetailsScreenModel {
        return DetailsScreenModel(
            id = id,
            image = images?.toImageModel(),
            name = name,
            type = type,
            subType = subtypes,
            level = level,
            hp = hp,
            attacks = attacks?.map { it.toAttackModel() },
            weaknesses = weaknesses?.map { it.toWeaknessModel() },
            abilities = abilities?.map { it.toAbilityModel() },
            resistances = resistances?.map { it.toResistanceModel() }
        )
    }
}

@Parcelize
data class ImageDto(
    val small: String? = null,
    val large: String? = null
) : Parcelable {
    fun toImageModel(): ImageModel {
        return ImageModel(
            small = small,
            large = large
        )
    }
}

@Parcelize
data class AttackDto(
    val name: String? = null,
    val cost: List<String>,
    val convertedEnergyCost: Int? = null,
    val damage: String? = null,
    val text: String? = null
) : Parcelable {
    fun toAttackModel(): AttackModel {
        return AttackModel(
            name = name,
            cost = cost,
            convertedEnergyCost = convertedEnergyCost,
            damage = damage,
            text = text
        )
    }
}

@Parcelize
data class WeaknessDto(
    val type: String? = null,
    val value: String? = null
) : Parcelable {
    fun toWeaknessModel(): WeaknessModel {
        return WeaknessModel(
            type = type,
            value = value
        )
    }
}

@Parcelize
data class AbilityDto(
    val name: String? = null,
    val text: String? = null,
    val type: String? = null
) : Parcelable {
    fun toAbilityModel(): AbilityModel {
        return AbilityModel(
            name = name,
            text = text,
            type = type
        )
    }
}

@Parcelize
data class ResistanceDto(
    val type: String? = null,
    val value: String? = null
) : Parcelable {
    fun toResistanceModel(): ResistanceModel {
        return ResistanceModel(
            type = type,
            value = value
        )
    }
}
