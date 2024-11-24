package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ProfileScreen(navController = navController, context = this)
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController, context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("FIRST_NAME", "") ?: ""
    val lastName = sharedPreferences.getString("LAST_NAME", "") ?: ""
    val email = sharedPreferences.getString("EMAIL", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Image
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your logo image file
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Profile Information Section
        Text("Profile information:", style = MaterialTheme.typography.headlineMedium)

        // Display User Data
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text("First Name: $firstName", style = MaterialTheme.typography.bodyLarge)
            Text("Last Name: $lastName", style = MaterialTheme.typography.bodyLarge)
            Text("Email: $email", style = MaterialTheme.typography.bodyLarge)
        }

        // Spacer to create space between profile info and the logout button
        Spacer(modifier = Modifier.weight(1f))

        // Log out Button
        Button(
            onClick = {
                // Clear shared preferences on logout
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                // Navigate back to onboarding screen
                navController.navigate("onboarding") {
                    popUpTo("profile") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Log out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(navController = rememberNavController(), context = LocalContext.current)
}
