package dev.devlopment.cred_assignment.Navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.devlopment.cred_assignment.Screens.BankSelectionScreen
import dev.devlopment.cred_assignment.Screens.EmiSelectionScreen
import dev.devlopment.cred_assignment.Screens.LoanAmountScreen
import dev.devlopment.cred_assignment.ViewModels.LoanViewModel


@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoanAmount.route
    ) {
        composable(Screen.LoanAmount.route) {
            LoanAmountScreen( viewModel = LoanViewModel(),onProceed ={navController.navigate(Screen.EmiSelection.route)} )
        }
        composable(Screen.EmiSelection.route) {
            EmiSelectionScreen(viewModel = LoanViewModel(), onProceed = {
                navController.navigate(Screen.BankSelection.route)
            })
        }
        composable(Screen.BankSelection.route) {
            BankSelectionScreen(viewModel = LoanViewModel(), onProceed = {
                navController.navigate(Screen.EmiSelection.route)
            })

        }
    }
}