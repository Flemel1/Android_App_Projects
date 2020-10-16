package com.example.footballscorecounter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.footballscorecounter.R
import com.example.footballscorecounter.viewmodel.SharedViewModel


class FragmentScoreA : Fragment() {

    val TAG = "ScoreTeamB"
    lateinit var model : SharedViewModel

    lateinit var team_name : TextView
    lateinit var btn_increase : Button
    lateinit var btn_decrease : Button
    lateinit var score_display : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            model = ViewModelProvider(it).get(SharedViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_score_a, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team_name = view.findViewById(R.id.tv_team_name_A)
        btn_increase = view.findViewById(R.id.btn_increase_A)
        btn_decrease = view.findViewById(R.id.btn_decrease_A)
        score_display = view.findViewById(R.id.tv_score_A)
        model.getTeamA().observe(viewLifecycleOwner, Observer {
            team_name.text = it
        })
        score_display.text = model.getScoreTeamA().toString()
        btn_increase.setOnClickListener {
            model.increaseScoreTeamA()
            Log.d(TAG, model.getScoreTeamB().toString())
            score_display.text = model.getScoreTeamA().toString()
        }
        btn_decrease.setOnClickListener {
            model.decreaseScoreTeamA()
            Log.d(TAG, model.getScoreTeamB().toString())
            score_display.text = model.getScoreTeamA().toString()
        }
    }
}