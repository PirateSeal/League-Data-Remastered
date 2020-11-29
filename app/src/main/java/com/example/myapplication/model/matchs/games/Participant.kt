package com.example.myapplication.model.matchs.games

data class Participant(
    val championId: Int,
    val participantId: Int,
    val spell1Id: Int,
    val spell2Id: Int,
    val stats: Stats,
    val teamId: Int,
    val timeline: Timeline
)