package com.example.newapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.databinding.SportsFragmentBinding
class SportsFragment : Fragment() {

    private lateinit var binding: SportsFragmentBinding
    private lateinit var sportsAdapter: SportsAdapter
    private val viewModel: SportsViewModel by viewModels{
        SportsViewModel.Factory(SportsRepository(AppDatabase.getDatabase(requireContext()).sportDao()))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SportsFragmentBinding.inflate(inflater, container, false)


        val gridLayoutManager = GridLayoutManager(context, 2)
        sportsAdapter = SportsAdapter(mutableListOf())

        binding.rvSports.apply {
            layoutManager = gridLayoutManager
            adapter = sportsAdapter
        }
//        sportsAdapter = SportsAdapter(mutableListOf())
//        binding.rvSports.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = sportsAdapter
//        }

        viewModel.sports.observe(viewLifecycleOwner) { sports ->
            sportsAdapter.updateSports(sports)
        }

        binding.fabAddSport.setOnClickListener {
            val dialog = AddSportDialogFragment()
            dialog.show(childFragmentManager, "AddSportDialog")
        }

        return binding.root
    }


}
