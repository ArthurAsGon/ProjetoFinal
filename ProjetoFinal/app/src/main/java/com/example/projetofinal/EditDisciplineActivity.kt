/*
package com.example.projetofinal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projetofinal.databinding.ActivityEditDisciplineBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditDisciplineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDisciplineBinding
    private lateinit var database: DatabaseReference
    private lateinit var disciplineId: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDisciplineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference.child("disciplines")
        disciplineId = intent.getStringExtra("DISCIPLINE_ID") ?: ""

        binding.saveButton.setOnClickListener {
            saveDiscipline()
        }

        loadDisciplineDetails()
    }

    private fun loadDisciplineDetails() {
        database.child(disciplineId).get().addOnSuccessListener {
            val discipline = it.getValue(Discipline::class.java)
            if (discipline != null) {
                binding.nameEditText.setText(discipline.name)
                binding.teacherEditText.setText(discipline.teacher)
                binding.dayEditText.setText(discipline.day)
                binding.timeEditText.setText(discipline.time)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load discipline details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDiscipline() {
        val name = binding.nameEditText.text.toString()
        val teacher = binding.teacherEditText.text.toString()
        val day = binding.dayEditText.text.toString()
        val time = binding.timeEditText.text.toString()

        val discipline = Discipline(disciplineId, name, teacher, day, time)
        database.child(disciplineId).setValue(discipline).addOnSuccessListener {
            Toast.makeText(this, "Discipline updated successfully", Toast.LENGTH_SHORT).show()
                   }.addOnFailureListener {
            Toast.makeText(this, "Failed to update discipline", Toast.LENGTH_SHORT).show()
        }
    }
}

 */