package com.example.cupidarrow.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cupidarrow.R
import com.example.cupidarrow.model.FeedCard
import com.example.cupidarrow.model.Gender
import com.example.cupidarrow.model.LookingForOption
import com.example.cupidarrow.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _feedProfiles = MutableStateFlow(sampleFeedProfiles())
    val feedProfiles: StateFlow<List<FeedCard>> = _feedProfiles.asStateFlow()

    fun updatePhone(phone: String) {
        _userProfile.update { it.copy(phone = phone) }
    }

    fun updateName(name: String) {
        _userProfile.update { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _userProfile.update { it.copy(email = email) }
    }

    fun updateAge(age: Int) {
        _userProfile.update { it.copy(age = age) }
    }

    fun updateGender(gender: Gender) {
        _userProfile.update { it.copy(gender = gender) }
    }

    fun updateLookingFor(option: LookingForOption) {
        _userProfile.update { it.copy(lookingFor = option) }
    }

    fun toggleInterest(interest: String) {
        _userProfile.update { current ->
            val set = current.interests.toMutableSet()
            if (set.contains(interest)) {
                set.remove(interest)
            } else {
                if (set.size < 3) set.add(interest)
            }
            current.copy(interests = set)
        }
    }

    fun addPhoto(photoUri: String) {
        _userProfile.update { current ->
            current.copy(photos = current.photos + photoUri)
        }
    }

    fun setLocationEnabled(enabled: Boolean) {
        _userProfile.update { it.copy(isLocationEnabled = enabled) }
    }

    fun popFeedCard() {
        _feedProfiles.update { list ->
            if (list.isNotEmpty()) list.drop(1) else sampleFeedProfiles()
        }
    }

    private fun sampleFeedProfiles(): List<FeedCard> = listOf(
        FeedCard(
            id = "1",
            name = "Карина",
            age = 22,
            location = "КАЛИНИНГРАД • 3 КМ ОТ ВАС",
            imageResId = R.drawable.photo_karina
        ),
        FeedCard(
            id = "2",
            name = "Джессика",
            age = 23,
            location = "СВЕТЛОГОРСК • 12 КМ ОТ ВАС",
            imageResId = R.drawable.photo_jessica
        ),
        FeedCard(
            id = "3",
            name = "София",
            age = 21,
            location = "ЗЕЛЕНОГРАДСК • 8 КМ ОТ ВАС",
            imageResId = R.drawable.photo_sofia
        ),
        FeedCard(
            id = "4",
            name = "Дарья",
            age = 24,
            location = "БАЛТИЙСК • 25 КМ ОТ ВАС",
            imageResId = R.drawable.photo_daria
        ),
        FeedCard(
            id = "5",
            name = "Виктория",
            age = 22,
            location = "КАЛИНИНГРАД • 5 КМ ОТ ВАС",
            imageResId = R.drawable.photo_victoria
        )
    )
}
