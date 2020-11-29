package com.tcousin.leaguedataremastered.model.ranked

data class MiniSeries(
    val losses: Int,
    val progress: String,
    val target: Int,
    val wins: Int
)