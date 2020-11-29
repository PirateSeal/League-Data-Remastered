package com.tcousin.leaguedataremastered.model.matchs.games

data class Team(
    val bans: List<Ban>,
    val baronKills: Int,
    val dominionVictoryScore: Int,
    val dragonKills: Int,
    val firstBaron: Boolean,
    val firstBlood: Boolean,
    val firstDragon: Boolean,
    val firstInhibitor: Boolean,
    val firstRiftHerald: Boolean,
    val firstTower: Boolean,
    val inhibitorKills: Int,
    val riftHeraldKills: Int,
    val teamId: Int,
    val towerKills: Int,
    val vilemawKills: Int,
    val win: String
)