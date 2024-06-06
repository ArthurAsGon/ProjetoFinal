package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentEditDisciplineBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditDisciplineFragment : Fragment() {

    private var _binding: FragmentEditDisciplineBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var disciplineId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditDisciplineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference.child("disciplines")
        disciplineId = arguments?.getString("DISCIPLINE_ID") ?: ""

        binding.saveButton.setOnClickListener {
            saveDiscipline()
        }

        loadDisciplineDetails()
    }

    private fun loadDisciplineDetails() {
        database.child(disciplineId).get().addOnSuccessListener {
            val disciplineMap = it.value as Map<String, Any>
            val id = disciplineMap["id"] as String
            val name = disciplineMap["name"] as String
            val teacher = disciplineMap["teacher"] as String
            val day = disciplineMap["day"] as String
            val time = disciplineMap["time"] as String
            val discipline = Discipline(id, name, teacher, day, time)

            binding.nameEditText.setText(discipline.name)
            binding.teacherEditText.setText(discipline.teacher)
            binding.dayEditText.setText(discipline.day)
            binding.timeEditText.setText(discipline.time)
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to load discipline details", Toast.LENGTH_SHORT).show()
        }
    }


    private fun saveDiscipline() {
        val name = binding.nameEditText.text.toString()
        val teacher = binding.teacherEditText.text.toString()
        val day = binding.dayEditText.text.toString()
        val time = binding.timeEditText.text.toString()

        val discipline = Discipline(disciplineId, name, teacher, day, time)
        database.child(disciplineId).setValue(discipline).addOnSuccessListener {
            Toast.makeText(requireContext(), "Discipline updated successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to update discipline", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
