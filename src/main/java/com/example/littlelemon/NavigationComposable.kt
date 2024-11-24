package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val startDestination = remember { getStartDestination(context) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            RegisterScreen(navController)
        }
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            Profile()
        }
    }
}

private fun getStartDestination(context: Context): String {
    val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    return if (sharedPreferences.getBoolean("USER_EXISTS", false)) Home.route else Onboarding.route
}
