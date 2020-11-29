package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.history.Historic
import com.example.myapplication.history.HistoricAdapter
import kotlinx.android.synthetic.main.fragment_historic.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoricFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoricFragment : Fragment() {

private lateinit var adapter: HistoricAdapter;
    private lateinit var historics: ArrayList<Historic>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        historic_recyclerView.layoutManager = LinearLayoutManager(requireContext());
val url = "https://images.unsplash.com/photo-1518806118471-f28b20a1d79d?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZmlsZXxlbnwwfHwwfA%3D%3D&w=1000&q=80";
        historics = ArrayList()
        for(x in 0..10){
            val h = Historic(profile_pic = url, kda ="$x", rank_p = url )
            historics.add(h);
        }

        adapter = HistoricAdapter(historics)
        historic_recyclerView.adapter = adapter;

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