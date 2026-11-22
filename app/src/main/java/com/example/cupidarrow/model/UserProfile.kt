package com.example.cupidarrow.model

data class UserProfile(
    val phone: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 23,
    val gender: Gender? = null,
    val lookingFor: LookingForOption? = null,
    val interests: Set<String> = emptySet(),
    val photos: List<String> = emptyList(),
    val isLocationEnabled: Boolean = false
)

enum class Gender(val label: String) {
    MALE("Male"),
    FEMALE("Female")
}

enum class LookingForOption(val label: String) {
    RELATIONSHIP("A relationship"),
    CASUAL("Somthing casual"),
    NOT_SURE("I'm not sure yet"),
    PREFER_NOT_TO_SAY("Prefer not to say")
}
