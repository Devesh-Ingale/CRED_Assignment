package dev.devlopment.cred_assignment.DataClasses

data class EmiPlan(
    val monthlyAmount: Int,
    val durationMonths: Int,
    val isRecommended: Boolean = false,
    val isSelected: Boolean = false
)