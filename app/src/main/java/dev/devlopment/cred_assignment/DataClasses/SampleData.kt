package dev.devlopment.cred_assignment.DataClasses


import dev.devlopment.cred_assignment.R

object SampleData {
    val emiPlans = listOf(
        EmiPlan(
            monthlyAmount = 4247,
            durationMonths = 12,
            isSelected = true
        ),
        EmiPlan(
            monthlyAmount = 5580,
            durationMonths = 9,
            isRecommended = true
        ),
        EmiPlan(
            monthlyAmount = 8200,
            durationMonths = 6
        )
    )

    val bankAccounts = listOf(
        BankAccount(
            bankName = "Paytm Payments Bank",
            accountNumber = "919935670475",
            icon = R.drawable.ic_launcher_foreground,
            isSelected = true
        )
        // Add more bank accounts as needed
    )
}