package com.example.cupidarrow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupidarrow.ui.screens.AgeScreen
import com.example.cupidarrow.ui.screens.EmailScreen
import com.example.cupidarrow.ui.screens.GenderScreen
import com.example.cupidarrow.ui.screens.GetStartedScreen
import com.example.cupidarrow.ui.screens.InterestsScreen
import com.example.cupidarrow.ui.screens.LocationScreen
import com.example.cupidarrow.ui.screens.LoginScreen
import com.example.cupidarrow.ui.screens.LookingForScreen
import com.example.cupidarrow.ui.screens.MainFeedScreen
import com.example.cupidarrow.ui.screens.NameScreen
import com.example.cupidarrow.ui.screens.OtpScreen
import com.example.cupidarrow.ui.screens.PhoneInputScreen
import com.example.cupidarrow.ui.screens.PhotoUploadScreen
import com.example.cupidarrow.ui.screens.SplashScreen
import com.example.cupidarrow.viewmodel.RegistrationViewModel

@Composable
fun AppNavigation(
    viewModel: RegistrationViewModel = viewModel()
) {
    val navController = rememberNavController()
    val userProfile by viewModel.userProfile.collectAsState()
    val feedProfiles by viewModel.feedProfiles.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.GetStarted.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.GetStarted.route) {
            GetStartedScreen(
                onLoginWithPhone = { navController.navigate(Screen.Login.route) },
                onSignUp = { navController.navigate(Screen.PhoneInput.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onContinue = { phone ->
                    viewModel.updatePhone(phone)
                    navController.navigate(Screen.Otp.route)
                },
                onSignUp = { navController.navigate(Screen.PhoneInput.route) }
            )
        }

        composable(Screen.PhoneInput.route) {
            PhoneInputScreen(
                initialPhone = userProfile.phone,
                onBackClick = { navController.popBackStack() },
                onContinue = { phone ->
                    viewModel.updatePhone(phone)
                    navController.navigate(Screen.Otp.route)
                }
            )
        }

        composable(Screen.Otp.route) {
            OtpScreen(
                phone = userProfile.phone,
                onBackClick = { navController.popBackStack() },
                onVerify = { navController.navigate(Screen.Name.route) }
            )
        }

        composable(Screen.Name.route) {
            NameScreen(
                initialName = userProfile.name,
                onBackClick = { navController.popBackStack() },
                onContinue = { name ->
                    viewModel.updateName(name)
                    navController.navigate(Screen.Email.route)
                }
            )
        }

        composable(Screen.Email.route) {
            EmailScreen(
                initialEmail = userProfile.email,
                onBackClick = { navController.popBackStack() },
                onContinue = { email ->
                    viewModel.updateEmail(email)
                    navController.navigate(Screen.Age.route)
                }
            )
        }

        composable(Screen.Age.route) {
            AgeScreen(
                initialAge = userProfile.age,
                onBackClick = { navController.popBackStack() },
                onContinue = { age ->
                    viewModel.updateAge(age)
                    navController.navigate(Screen.Gender.route)
                }
            )
        }

        composable(Screen.Gender.route) {
            GenderScreen(
                initialGender = userProfile.gender,
                onBackClick = { navController.popBackStack() },
                onContinue = { gender ->
                    viewModel.updateGender(gender)
                    navController.navigate(Screen.LookingFor.route)
                }
            )
        }

        composable(Screen.LookingFor.route) {
            LookingForScreen(
                initialOption = userProfile.lookingFor,
                onBackClick = { navController.popBackStack() },
                onContinue = { option ->
                    viewModel.updateLookingFor(option)
                    navController.navigate(Screen.Interests.route)
                }
            )
        }

        composable(Screen.Interests.route) {
            InterestsScreen(
                initialInterests = userProfile.interests,
                onBackClick = { navController.popBackStack() },
                onContinue = { interests ->
                    interests.forEach { viewModel.toggleInterest(it) }
                    navController.navigate(Screen.PhotoUpload.route)
                }
            )
        }

        composable(Screen.PhotoUpload.route) {
            PhotoUploadScreen(
                initialPhotos = userProfile.photos,
                onBackClick = { navController.popBackStack() },
                onContinue = { photos ->
                    photos.forEach { viewModel.addPhoto(it) }
                    navController.navigate(Screen.Location.route)
                }
            )
        }

        composable(Screen.Location.route) {
            LocationScreen(
                onLocationGranted = { isGranted ->
                    viewModel.setLocationEnabled(isGranted)
                    navController.navigate(Screen.MainFeed.route) {
                        popUpTo(Screen.GetStarted.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.MainFeed.route) {
            MainFeedScreen(
                cards = feedProfiles,
                onCardSwiped = { viewModel.popFeedCard() }
            )
        }
    }
}
