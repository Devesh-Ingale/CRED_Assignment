package dev.devlopment.cred_assignment.API

data class ApiResponse(
    val items: List<Item>
)

data class Item(
    val open_state: OpenState,
    val closed_state: ClosedState,
    val cta_text: String
)

data class OpenState(
    val body: Body
)

data class ClosedState(
    val body: Map<String, String>
)

data class Body(
    val title: String,
    val subtitle: String,
    val card: Card? = null,
    val items: List<EmiPlan2>? = null,
    val footer: String
)

data class Card(
    val header: String,
    val description: String,
    val max_range: Int,
    val min_range: Int
)

data class EmiPlan2(
    val emi: String,
    val duration: String,
    val title: String,
    val subtitle: String,
    val tag: String? = null
)
