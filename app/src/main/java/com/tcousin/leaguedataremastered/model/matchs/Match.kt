package com.tcousin.leaguedataremastered.model.matchs

data class Match(
    val champion: Int,
    val gameId: Long,
    val lane: String,
    val platformId: String,
    val queue: Int,
    val role: String,
    val season: Int,
    val timestamp: Long
)