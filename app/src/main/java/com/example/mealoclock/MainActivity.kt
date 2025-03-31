package com.example.mealoclock


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.mealoclock.ui.theme.MealOClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealOClockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MealOClock(
                        name = "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




@Composable
fun MealOClock(name: String, modifier: Modifier) {
    var timeOfDay by remember {
        mutableStateOf(name)
    }
    var mealSuggestion by remember {
        mutableStateOf(name)
    }
    var mealImage by remember {
        mutableStateOf(0) //Image resource ID
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .fillMaxSize() // Fill available screen space
            .background(Color(0xFFFFE0B2)) // Soft pastel orange for warmth
            .padding(20.dp) // Improved spacing for visual balance
    ) {
        // App Title with enhanced styling
        Text(
            text = "Meal O Clock",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6D4C41) // Deep brown for a cozy look
        )

        Divider(color = Color(0xFF8D6E63), thickness = 2.dp)
        Spacer(modifier = Modifier.size(30.dp))

        // Text Field for user input
        OutlinedTextField(
            value = timeOfDay,
            onValueChange = { text ->
                timeOfDay = text
            },
            placeholder = { Text(text = "Enter Time of Day") },
            modifier = Modifier.background(Color(0xFFFFF8E1)) // Soft background color for text field
        )

        Spacer(modifier = Modifier.size(30.dp))

        // Row of Buttons for suggesting and resetting meals
        Row {
            Button(onClick = {
                // Assign meal suggestions and images based on time of day
                when (timeOfDay.lowercase()) {
                    "morning" -> {
                        mealSuggestion = "Breakfast: Pancakes with Honey"
                        mealImage = R.drawable.pancakes
                    }
                    "mid-morning snack" -> {
                        mealSuggestion = "Light snack: Donuts"
                        mealImage = R.drawable.donuts
                    }
                    "afternoon" -> {
                        mealSuggestion = "Lunch: Homemade BBQ Chicken Pizza"
                        mealImage = R.drawable.pizza
                    }
                    "afternoon snack" -> {
                        mealSuggestion = "Afternoon snack: Chocolate Chip Cookies"
                        mealImage = R.drawable.chipscookies
                    }
                    "dinner" -> {
                        mealSuggestion = "Main course: Mac and Cheese Baked"
                        mealImage = R.drawable.macandcheese
                    }
                    "after dinner snack" -> {
                        mealSuggestion = "Dessert: Chocolate Lava Cake"
                        mealImage = R.drawable.cake
                    }
                    else -> {
                        mealSuggestion = "Invalid entry. Please enter Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner, or After Dinner"
                        mealImage = 0 // No image for invalid input
                    }
                }
            }) {
                Text(text = "Meal Suggestion")
            }

            // Reset Button to clear inputs and suggestions
            Button(onClick = {
                timeOfDay = name
                mealSuggestion = name
                mealImage = 0
            }) {
                Text(text = "Reset")
            }
        }

        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = mealSuggestion,
            fontSize = 18.sp,
            color = Color(0xFF4E342E) // Rich brown for elegance
        )

        // Display selected meal image (if available)
        if (mealImage != 0) {
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                painter = painterResource(id = mealImage),
                contentDescription = "Meal Image",
                modifier = Modifier
                    .size(180.dp)
                    .padding(10.dp) // Balanced spacing for images
            )
        }
    }
}


