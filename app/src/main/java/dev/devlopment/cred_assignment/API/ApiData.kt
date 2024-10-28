package dev.devlopment.cred_assignment.API

import retrofit2.http.Body

data class ApiResponse(
    val items: List<Item>
)

data class Item(
    val open_state: OpenState,
    val closed_state: ClosedState,
    val cta_text: String
)

data class OpenState(
    val body: OpenBody
)

data class ClosedState(
    val body: ClosedBody
)


data class ClosedBody(
    val key1: String? = null, // e.g., "emi"
    val key2: String? = null  // e.g., "duration"
)
data class OpenBody(
    val title: String,
    val subtitle: String,
    val card: Card? = null,
    val items: List<EmiItem>? = null,
    val footer: String
)

data class Card(
    val header: String,
    val description: String,
    val max_range: Int,
    val min_range: Int
)

data class EmiItem(
    val emi: String? = null,
    val duration: String? = null,
    val title: String,
    val subtitle: String,
    val tag: String? = null,
    val icon: String? = null
)