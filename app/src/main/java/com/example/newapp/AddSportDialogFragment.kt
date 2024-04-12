package com.example.newapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.newapp.databinding.DialogAddSportBinding
import androidx.fragment.app.viewModels


class AddSportDialogFragment : DialogFragment() {
    // Assuming you have a ViewModel set up for your sports
    private val viewModel: SportsViewModel by viewModels {
        // Assuming you have a way to obtain an instance of SportsRepository
        SportsViewModel.Factory(getSportsRepository())
    }

    private fun getSportsRepository(): SportsRepository {
        // Return an instance of SportsRepository
        // Replace this with your actual implementation
        val sportDao = AppDatabase.getDatabase(requireContext()).sportDao()
        return SportsRepository(sportDao)
    }
    // Using view binding
    private var _binding: DialogAddSportBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        // Get the current dialog window
        val window = dialog?.window
        // Get the screen width
        val screenWidth = resources.displayMetrics.widthPixels
        // Set the dialog width to 90% of the screen width
        window?.setLayout((screenWidth * 0.9).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        // You can also set the dialog position or other properties here
    }
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
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()

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
