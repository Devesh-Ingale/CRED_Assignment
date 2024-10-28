package dev.devlopment.cred_assignment.Navigations

import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Navigator {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.LoanAmount)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun goBack() {
        when (_currentScreen.value) {
            is Screen.BankSelection -> navigateTo(Screen.EmiSelection)
            is Screen.EmiSelection -> navigateTo(Screen.LoanAmount)
            is Screen.LoanAmount -> { /* Handle exit */ }
        }
    }
}
