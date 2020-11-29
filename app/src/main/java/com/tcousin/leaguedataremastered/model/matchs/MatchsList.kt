package com.tcousin.leaguedataremastered.model.matchs

data class MatchsList(
    val endIndex: Int,
    val matches: List<Match>,
    val startIndex: Int,
    val totalGames: Int
)