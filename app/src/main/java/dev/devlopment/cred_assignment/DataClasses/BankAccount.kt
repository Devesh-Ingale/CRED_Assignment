package dev.devlopment.cred_assignment.DataClasses

data class BankAccount(
    val bankName: String,
    val accountNumber: String,
    val icon: Int,
    val isSelected: Boolean = false
)