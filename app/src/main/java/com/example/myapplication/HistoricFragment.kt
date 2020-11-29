package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.database.DataStorage
import com.example.myapplication.history.Historic
import com.example.myapplication.history.HistoricAdapter
import com.example.myapplication.http.historic.HistoricServiceImpl
import com.example.myapplication.http.servicepatch.ApiCdnServiceImpl
import com.example.myapplication.model.matchs.MatchsList
import com.example.myapplication.model.matchs.games.Game
import com.example.myapplication.model.matchs.games.Participant
import kotlinx.android.synthetic.main.fragment_historic.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoricFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoricFragment : Fragment() {

    private lateinit var gameInfo: Game;
    private val api = HistoricServiceImpl
    private val cdnApi = ApiCdnServiceImpl

    private lateinit var adapter: HistoricAdapter;
    private lateinit var historics: ArrayList<Historic>;
    private lateinit var dataStorage: DataStorage;
    private lateinit var accountId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStorage = DataStorage(requireContext())

        historic_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        historics = ArrayList()

        adapter = HistoricAdapter(historics)
        historic_recyclerView.adapter = adapter;
        accountId = dataStorage.getString("accountId")
        api.getMatches(accountId)

        api.setFiller(object : HistoricServiceImpl.InfoFiller {

            override fun fillMatchInfo(game: Game) {
                gameInfo = game;
                println("GAME")
                println(game)
            }

            override suspend fun fillMatchList(matchList: MatchsList) {

                var player: Participant

                for (match in matchList.matches) {
                    for (participant in gameInfo.participantIdentities) {
                        if (accountId == participant.player.currentAccountId) {
                            val playerGameId = participant.participantId
                            player =
                                gameInfo.participants.find { p -> p.participantId == playerGameId }!!

                            cdnApi.getChampion(player.championId)

                            val champion: String = ""

                            val historic = Historic(
                                championId = champion,
                                kills = player.stats.kills,
                                deaths = player.stats.deaths,
                                assists = player.stats.assists,
                                item0 = player.stats.item0,
                                item1 = player.stats.item1,
                                item2 = player.stats.item2,
                                item3 = player.stats.item3,
                                item4 = player.stats.item4,
                                item5 = player.stats.item5,
                                ward = player.stats.item6
                            )
                            historics.add(historic)
                        }
                    }
                    println(historics);
                    println(historics);
                }
            }
        })

        api.setListener(object : HistoricServiceImpl.ErrorHandler {
            override fun errorMatches() {
                Toast.makeText(requireContext(), R.string.error_match, Toast.LENGTH_LONG).show()
            }
        })


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HistoricFragment.
         */
        @JvmStatic
        fun newInstance() =
            HistoricFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}