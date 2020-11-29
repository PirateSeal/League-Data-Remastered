package com.example.myapplication.model.matchs.games

data class Game(
    val gameCreation: Long,
    val gameDuration: Int,
    val gameId: Long,
    val gameMode: String,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val participantIdentities: List<ParticipantIdentity>,
    val participants: List<Participant>,
    val platformId: String,
    val queueId: Int,
    val seasonId: Int,
    val teams: List<Team>
)