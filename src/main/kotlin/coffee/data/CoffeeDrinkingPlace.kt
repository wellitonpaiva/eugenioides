package coffee.data

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeDrinkingPlace(
    val home: Boolean,
    val office: Boolean,
    val onTheGo: Boolean,
    val cafe: Boolean,
    val other: Boolean
) {
    constructor(map: Map<String, String>) : this(
        home = map["Where do you typically drink coffee? (At home)"].toBoolean(),
        office = map["Where do you typically drink coffee? (At the office)"].toBoolean(),
        onTheGo = map["Where do you typically drink coffee? (On the go)"].toBoolean(),
        cafe = map["Where do you typically drink coffee? (At a cafe)"].toBoolean(),
        other = map["Where do you typically drink coffee? (None of these)"].toBoolean(),
    )
}

@Serializable
enum class PreferredDrinkingPlace {
    HOME, OFFICE, ON_THE_GO, CAFE, OTHER
}