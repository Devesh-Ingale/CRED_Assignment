package dev.devlopment.cred_assignment.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.devlopment.cred_assignment.API.EmiItem
import dev.devlopment.cred_assignment.API.Item

import dev.devlopment.cred_assignment.ViewModels.LoanViewModel


@Composable
fun EmiSelectionScreen(viewModel: LoanViewModel,secondItem: Item?, onProceed: () -> Unit) {
    val emiPlans = listOf(
        EmiItem(
            title = secondItem?.open_state?.body?.items?.get(0)?.title.toString(),
            subtitle = secondItem?.open_state?.body?.items?.get(0)?.subtitle.toString(),
            emi = secondItem?.open_state?.body?.items?.get(0)?.emi.toString(),
            duration = secondItem?.open_state?.body?.items?.get(0)?.duration.toString(),
        ),
        EmiItem(
            title = secondItem?.open_state?.body?.items?.get(1)?.title.toString(),
            subtitle = secondItem?.open_state?.body?.items?.get(1)?.subtitle.toString(),
            emi = secondItem?.open_state?.body?.items?.get(1)?.emi.toString(),
            duration = secondItem?.open_state?.body?.items?.get(1)?.duration.toString(),
            tag = secondItem?.open_state?.body?.items?.get(1)?.tag.toString()
        ),
        EmiItem(
            title = secondItem?.open_state?.body?.items?.get(2)?.title.toString(),
            subtitle = secondItem?.open_state?.body?.items?.get(2)?.subtitle.toString(),
            emi = secondItem?.open_state?.body?.items?.get(2)?.emi.toString(),
            duration = secondItem?.open_state?.body?.items?.get(2)?.duration.toString(),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF1E1E1E),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            secondItem?.open_state?.body?.title?.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            secondItem?.open_state?.body?.subtitle?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(emiPlans) { plan ->
                    EmiPlanCard(
                        plan = plan,
                        onSelect = { /* Handle plan selection */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { /* Handle custom plan */ },
                modifier = Modifier.wrapContentSize(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.White)
            ) {
                secondItem?.open_state?.body?.footer?.let { Text(it) }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onProceed,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                secondItem?.cta_text?.let { Text(it, fontSize = 18.sp) }
            }
        }
    }
}

@Composable
fun EmiPlanCard(
    plan: EmiItem,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(175.dp)
            .wrapContentHeight()
            .clickable(onClick = onSelect)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF7D6D8E)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Icon(
                    imageVector =  Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                plan.emi?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                plan.duration?.let {
                    Text(
                        text = it,
                        color = Color(0xFFB0B3BC),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "See calculations",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        if (plan.tag=="recommended") {
            Text(
                text = "recommended",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-10).dp)
                    .background(
                        color = Color(0xFFD1D5DB),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}


