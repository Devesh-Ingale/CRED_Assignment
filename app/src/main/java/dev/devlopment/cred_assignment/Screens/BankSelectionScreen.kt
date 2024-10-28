package dev.devlopment.cred_assignment.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.devlopment.cred_assignment.API.Card
import dev.devlopment.cred_assignment.API.EmiItem
import dev.devlopment.cred_assignment.API.Item
import dev.devlopment.cred_assignment.R
import dev.devlopment.cred_assignment.ViewModels.LoanViewModel

@Composable
fun BankSelectionScreen(viewModel: LoanViewModel, thirdItem: Item?, onProceed: () -> Unit) {
    val bankAccounts = listOf(
        EmiItem(
            icon = thirdItem?.open_state?.body?.items?.get(0)?.icon.toString(),  // replace with actual icon if available
            title = thirdItem?.open_state?.body?.items?.get(0)?.title.toString(),
            subtitle = thirdItem?.open_state?.body?.items?.get(0)?.subtitle.toString(),
             // Default selection, adjust as per logic
        ),
        EmiItem(
            icon = thirdItem?.open_state?.body?.items?.get(1)?.icon.toString(),  // replace with actual icon if available
            title = thirdItem?.open_state?.body?.items?.get(1)?.title.toString(),
            subtitle = thirdItem?.open_state?.body?.items?.get(1)?.subtitle!!.toString(),
            // Default selection, adjust as per logic
        ),
        EmiItem(
            icon = thirdItem?.open_state?.body?.items?.get(2)?.icon.toString(),  // replace with actual icon if available
            title = thirdItem?.open_state?.body?.items?.get(2)?.title.toString(),
            subtitle = thirdItem?.open_state?.body?.items?.get(2)?.subtitle!!.toString(),
            // Default selection, adjust as per logic
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
    ) {
        Text(
            text = thirdItem.open_state.body.title,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bankAccounts) { account ->
                BankAccountItem(
                    account = account,
                    onSelect = {  }
                )
            }
        }

        OutlinedButton(
            onClick = { /* Handle account change */ },
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {
            Text(thirdItem?.open_state?.body?.footer.toString())
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onProceed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Text(thirdItem?.cta_text.toString())
        }
    }
}
@Composable
fun BankAccountItem(
    account: EmiItem,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = account.title,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
            )

            Column {
                Text(
                    text = account.title,
                    color = Color.White
                )

                Text(
                    text = account.subtitle.toString(),
                    color = Color.Gray
                )
            }
        }

    }
}