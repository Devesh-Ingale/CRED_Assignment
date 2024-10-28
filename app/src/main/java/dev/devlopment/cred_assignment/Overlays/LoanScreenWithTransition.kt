package dev.devlopment.cred_assignment.Overlays
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.devlopment.cred_assignment.API.ApiResponse
import dev.devlopment.cred_assignment.Screens.BankSelectionScreen
import dev.devlopment.cred_assignment.Screens.EmiSelectionScreen
import dev.devlopment.cred_assignment.Screens.LoanAmountScreen
import dev.devlopment.cred_assignment.ViewModels.LoanViewModel

@Composable
fun LoanScreenWithTransition(viewModel: LoanViewModel,apiResponse: ApiResponse) {
    val item1 = apiResponse.items.firstOrNull()
    val item2 = apiResponse.items.getOrNull(1)
    val item3 = apiResponse.items.getOrNull(2)
    var currentScreen by remember { mutableStateOf(0) } // 0: Loan Amount, 1: EMI Selection, 2: Bank Selection
    var selectedAmount = remember { mutableStateOf("150,000") }
    var selectedEmi by remember { mutableStateOf("4,247") }
    var selectedDuration by remember { mutableStateOf("12 months") }

    // Handle the back button to navigate to previous screen
    BackHandler(enabled = currentScreen > 0) {
        currentScreen -= 1
    }
    // Animation states for all three screens
    val firstScreenOffset by animateFloatAsState(
        targetValue = when(currentScreen) {
            0 -> 0f
            else -> -1000f
        },
        animationSpec = tween(300),
        label = "firstScreenOffset"
    )

    val secondScreenOffset by animateFloatAsState(
        targetValue = when(currentScreen) {
            0 -> 1000f
            1 -> 0f
            else -> -1000f
        },
        animationSpec = tween(300),
        label = "secondScreenOffset"
    )

    val thirdScreenOffset by animateFloatAsState(
        targetValue = when(currentScreen) {
            0, 1 -> 1000f
            else -> 0f
        },
        animationSpec = tween(300),
        label = "thirdScreenOffset"
    )

    // Headers animation
    val firstHeaderOffset by animateFloatAsState(
        targetValue = if (currentScreen >= 1) 0f else -1000f,
        animationSpec = tween(300),
        label = "firstHeaderOffset"
    )

    val secondHeaderOffset by animateFloatAsState(
        targetValue = if (currentScreen >= 2) 0f else -1000f,
        animationSpec = tween(300),
        label = "secondHeaderOffset"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
    ) {
        // First Screen (Loan Amount)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = firstScreenOffset.dp)
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .zIndex(1f)
        ) {
            LoanAmountScreen(
                selectedAmount = selectedAmount,
                item1,
                onProceed = {
                    currentScreen = 1
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(3f)

        ) {
            // First Header (Credit Amount)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = firstHeaderOffset.dp)
                    .background(Color(0Xff212f45), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                    .clickable(enabled = currentScreen >= 1) {
                        currentScreen = 0
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    item1?.closed_state?.body?.key1.let {
                        if (it != null) {
                            Text(
                                text = it,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "₹${selectedAmount.value}",
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }
                }
            }

            // Second Header (EMI Details)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = secondHeaderOffset.dp)
                    .background(Color(0xFF1b3a4b), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                    .clickable(enabled = currentScreen >= 2) {
                        currentScreen = 1
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = item2?.closed_state?.body?.key1.toString(),
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = selectedEmi,
                                color = Color.Gray,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                text = item2?.closed_state?.body?.key2.toString(),
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = selectedDuration,
                                color = Color.Gray,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        // Second Screen (EMI Selection)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = secondScreenOffset.dp)
                .zIndex(if (currentScreen == 1) 2f else 0f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = if (currentScreen >= 1) 80.dp else 0.dp)
                    .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            ) {
                EmiSelectionScreen(
                    item2,
                    onProceed = {
                        currentScreen = 2

                    },
                    onSelectEmiPlan = { emi, duration -> // New callback for selecting EMI
                        selectedEmi = emi
                        selectedDuration = duration
                    }
                )
            }
        }

        // Third Screen (Bank Selection)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = thirdScreenOffset.dp)
                .zIndex(if (currentScreen == 2) 2f else 0f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = if (currentScreen >= 2) 160.dp else 0.dp)
                    .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            ) {
                BankSelectionScreen(
                    viewModel = viewModel,
                    thirdItem = item3,
                    onProceed = {
                        // Handle final proceed action
                    }
                )
            }
        }
    }
}