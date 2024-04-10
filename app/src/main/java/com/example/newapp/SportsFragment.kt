package com.example.newapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newapp.databinding.SportsFragmentBinding

class SportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SportsFragmentBinding.inflate(inflater, container, false)

        binding.fabAddSport.setOnClickListener {
            val dialog = AddSportDialogFragment()
            dialog.show(childFragmentManager, "AddSportDialog")
        }

        // Other setup code for your RecyclerView, etc.

        return binding.root
    }
}
