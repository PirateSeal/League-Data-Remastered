package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.http.ApiServiceImpl
import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    var images = intArrayOf(
        R.drawable.unranked,
        R.drawable.unranked
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var version = ""
        api.getPatchVersion()

        val profileIcon = profile_pic as ImageView
        profileIcon.setImageResource(R.drawable.summph)

        val editText = profile_search as EditText
        editText.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val summonerName = editText.text.toString()
                api.getSummoner(summonerName)
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })


        val carouselView = profile_carousel as CarouselView
        carouselView.setImageListener(imageListener)
        carouselView.pageCount = images.size

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
                    .into(profile_pic);
            }

            override fun fillPatch(patch: String) {
                version = patch
            }

            override fun fillRanked(modelRank: ModelRank) {
                TODO("Not yet implemented")
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

            override fun errorPatch() {
                Toast.makeText(
                    requireContext(),
                    "Could not find current patch version.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
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
        ImageListener { position, imageView -> // You can use Glide or Picasso here
            imageView.setImageResource(images[position])
        }
}