package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.http.ApiServiceImpl
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

        val profileIcon = profile_pic as ImageView
        profileIcon.setImageResource(R.drawable.summph)

        val carouselView = profile_carousel as CarouselView;
        carouselView.setImageListener(imageListener);
        carouselView.pageCount = images.size;

        ApiServiceImpl.setListener(object : ApiServiceImpl.ErrorHandler {
            override fun errorSummoner() {
                Toast.makeText(
                    requireContext(),
                    "Could not find this summoner",
                    Toast.LENGTH_LONG
                ).show()

            }

            override fun errorRank() {
                Toast.makeText(
                    requireContext(),
                    "Could not find this summoner",
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



    private var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageView.setImageResource(images[position])
        }
    }
}