package dev.devlopment.cred_assignment.Navigations

sealed class Screen(val route:String) {
    data object LoanAmount : Screen("loanamount")
    data object EmiSelection : Screen("emiselection")
    data object BankSelection : Screen("bankselection")
}