package com.example.newapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.newapp.databinding.DialogAddSportBinding
import androidx.fragment.app.viewModels


class AddSportDialogFragment : DialogFragment() {
    // Assuming you have a ViewModel set up for your sports
    private val viewModel: SportsViewModel by viewModels()

    // Using view binding
    private var _binding: DialogAddSportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddSportBinding.inflate(inflater, container, false)
        val view = binding.root

        // Populate the spinner with the sport types
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sport_types_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSportType.adapter = adapter
        }

        binding.buttonAddSport.setOnClickListener {
            val sportType = binding.spinnerSportType.selectedItem.toString()
            val sportName = binding.editTextSportName.text.toString().trim()
            val instructions = binding.editTextInstructions.text.toString().trim()

            // Input validation can also be added here

            if (sportType.isNotEmpty() && sportName.isNotEmpty() && instructions.isNotEmpty()) {
                // Use ViewModel to handle database operations
                viewModel.addNewSport(sportType, sportName, instructions)
            } else {
                // Handle the error case where some fields are empty
            }

            dismiss() // Close the dialog
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leak
    }
}
