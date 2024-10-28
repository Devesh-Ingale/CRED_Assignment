package dev.devlopment.cred_assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dev.devlopment.cred_assignment.API.ApiResponse
import dev.devlopment.cred_assignment.API.ApiState
import dev.devlopment.cred_assignment.Navigations.NavigationGraph
import dev.devlopment.cred_assignment.Navigations.Navigator
import dev.devlopment.cred_assignment.Overlays.LoanScreenWithTransition
import dev.devlopment.cred_assignment.Overlays.LoanScreenWithTransition2
import dev.devlopment.cred_assignment.Screens.TopBar
import dev.devlopment.cred_assignment.ViewModels.LoanViewModel
import dev.devlopment.cred_assignment.ui.theme.CRED_AssignmentTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()

            val viewModel: LoanViewModel by viewModels()
            val apiState by viewModel.apiState.collectAsState()

            when (apiState) {
                is ApiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is ApiState.Success -> {
                    val data = (apiState as ApiState.Success).data
                    // You can now access your data here
                    // data.items contains your List<Item>
                    Text(text = "Data loaded successfully!")
                }
                is ApiState.Error -> {
                    val error = (apiState as ApiState.Error).message
                    Text(text = "Error: $error")
                }
            }


            CRED_AssignmentTheme {

                Column {

                    TopBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    LoanScreenWithTransition2( viewModel = LoanViewModel())
                    //LoanScreenWithTransition(viewModel = LoanViewModel())
                }

                   //NavigationGraph(navController = navController)
            }
        }
    }
}

