package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.myapplication.database.DataStorage
import com.example.myapplication.http.serviceapi.ApiServiceImpl
import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    val api = ApiServiceImpl;

    private lateinit var dataStorage: DataStorage;

    private lateinit var version : String;
    private var summonerName: String = "";

    var imagesPh = intArrayOf(
        R.drawable.unranked,
        R.drawable.unranked
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStorage = DataStorage(requireContext())

        version = dataStorage.getString("patch")

        val profileIcon = profile_pic as CircleImageView
        profileIcon.setImageResource(R.drawable.summph)

        val editText = profile_search as EditText;
        summonerName = dataStorage.getString("summonerName")
        if(summonerName != ""){
            editText.setText(summonerName);
            api.getSummoner(summonerName);
        }

        editText.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val summonerName = editText.text.toString()
                dataStorage.putString("summonerName", summonerName);
                api.getSummoner(summonerName);
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        val carouselView = profile_carousel as CarouselView
        carouselView.setImageListener(imageListener)
        carouselView.pageCount = imagesPh.size

        ApiServiceImpl.setFiller(object : ApiServiceImpl.InfoFiller {
            override fun fillSummonerData(summoner: ModelSummoner) {
                val textLvl =
                    "${resources.getString(R.string.level)}: ${summoner.summonerLevel}"
                val profilIconUrl =
                    "${BuildConfig.CDN_URL}cdn/$version/img/profileicon/${summoner.profileIconId}.png"

                println(profilIconUrl)
                profile_lvl.text = textLvl

                Glide.with(view)
                    .load(profilIconUrl)
                    .into(profile_pic)

                api.getSummonerRanks(summoner.id)
            }

            override fun fillRanked(modelRank: ModelRank) {

                //Change caroussel images
                var rankedImages = intArrayOf()

                for (item in modelRank) {
                    rankedImages = when (item.tier.toLowerCase()) {
                        "bronze" -> rankedImages.plus(R.drawable.bronze)
                        "silver" -> rankedImages.plus(R.drawable.silver)
                        "gold" -> rankedImages.plus(R.drawable.gold)
                        "platinum" -> rankedImages.plus(R.drawable.platinum)
                        "diamond" -> rankedImages.plus(R.drawable.diamond)
                        "master" -> rankedImages.plus(R.drawable.master)
                        "grandmaster" -> rankedImages.plus(R.drawable.grandmaster)
                        "challenger" -> rankedImages.plus(R.drawable.challenger)
                        else -> rankedImages.plus(R.drawable.unranked)
                    }
                }

                val imageRankedListener =
                    ImageListener { position, imageView ->
                        imageView.setImageResource(rankedImages[position])
                    }

                fillRankLayout(0, modelRank)

                carouselView.setImageListener(imageRankedListener)
                carouselView.pageCount = rankedImages.size

                carouselView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        fillRankLayout(position, modelRank)
                    }
                })
            }
        })

        ApiServiceImpl.setListener(object : ApiServiceImpl.ErrorHandler {
            override fun errorSummoner() {
                Toast.makeText(
                    requireContext(),
                    "Could not find this summoner.",
                    Toast.LENGTH_LONG
                ).show()

            }

            override fun errorRank() {
                Toast.makeText(
                    requireContext(),
                    "Could not find this summoner.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun fillRankLayout(position: Int, modelRank: ModelRank) {
        val display = "${modelRank[position].tier} : ${modelRank[position].rank}"
        var lp = ""

        league_display.text = display

        if (modelRank[position].leaguePoints == 100) {
            for (i in modelRank[position].miniSeries.wins downTo 0) {
                lp += "✅ / "
            }
            for (i in modelRank[position].miniSeries.losses downTo 1) {
                lp += if (i == 1) {
                    "❌"
                } else {
                    "❌ / "
                }
            }
            lp_display.text = lp
        } else {
            lp = "${modelRank[position].leaguePoints} LP"
            lp_display.text = lp
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private var imageListener: ImageListener =
        ImageListener { position, imageView ->
            imageView.setImageResource(imagesPh[position])
        }


}