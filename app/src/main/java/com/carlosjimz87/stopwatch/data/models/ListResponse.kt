package com.carlosjimz87.stopwatch.data.models

data class ListItem(
    val id: String
)

data class ListResponse(
    val records: List<ListItem>,
    val success: Boolean
)