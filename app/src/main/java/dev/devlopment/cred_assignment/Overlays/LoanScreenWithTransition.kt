package dev.devlopment.cred_assignment.Overlays

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.devlopment.cred_assignment.Screens.EmiSelectionScreen
import dev.devlopment.cred_assignment.Screens.LoanAmountScreen
import dev.devlopment.cred_assignment.Screens.TopBar
import dev.devlopment.cred_assignment.ViewModels.LoanViewModel
import kotlinx.coroutines.launch

@Composable
fun LoanScreenWithTransition(viewModel: LoanViewModel) {
    var isSecondScreenVisible by remember { mutableStateOf(false) }
    var selectedAmount by remember { mutableStateOf("150,000") }

    // Animation states
    val firstScreenOffset by animateFloatAsState(
        targetValue = if (isSecondScreenVisible) -1000f else 0f,
        animationSpec = tween(300),
        label = "firstScreenOffset"
    )

    val secondScreenOffset by animateFloatAsState(
        targetValue = if (isSecondScreenVisible) 0f else 1000f,
        animationSpec = tween(300),
        label = "secondScreenOffset"
    )

    val headerOffset by animateFloatAsState(
        targetValue = if (isSecondScreenVisible) 0f else -1000f,
        animationSpec = tween(300),
        label = "headerOffset"
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
                viewModel = LoanViewModel(),
                onProceed = {
                    isSecondScreenVisible = true
                }
            )
        }

        // Transition Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = headerOffset.dp)
                .zIndex(3f)
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .clickable(enabled = isSecondScreenVisible) {
                    isSecondScreenVisible = false
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "credit amount",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "₹$selectedAmount",
                        color = Color.White,
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

        // Second Screen (EMI Selection)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = secondScreenOffset.dp)
                .zIndex(if (isSecondScreenVisible) 1f else 0f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
                    .background(
                        color = Color(0xFF1E1E1E),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                EmiSelectionScreen(
                    viewModel = viewModel,
                    onProceed = { /* Handle proceed */ }
                )
            }
        }
    }
}


@Preview
@Composable
fun LoanScreenPreview() {
    val viewModel = LoanViewModel()
    LoanScreenWithTransition(viewModel)
}
