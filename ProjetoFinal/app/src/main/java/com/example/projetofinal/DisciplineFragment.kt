package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetofinal.databinding.FragmentDisciplineBinding
import com.google.firebase.database.*

class DisciplineFragment : Fragment(), DisciplineAdapter.OnItemClickListener {

    private var _binding: FragmentDisciplineBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var disciplineAdapter: DisciplineAdapter
    private val disciplineList = mutableListOf<Discipline>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisciplineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference.child("disciplines")
        disciplineAdapter = DisciplineAdapter(disciplineList, this)

        binding.btnVoltarMain.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAlterar.setOnClickListener {
            findNavController().navigate(R.id.action_disciplinesFragment_to_editDisciplineFragment)
        }


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = disciplineAdapter
        }

        fetchDisciplines()
    }

    private fun fetchDisciplines() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                disciplineList.clear()
                for (dataSnapshot in snapshot.children) {
                    val disciplineData = dataSnapshot.value
                    Log.d("DisciplineData", disciplineData.toString()) // Imprimir dados brutos
                    val discipline = dataSnapshot.getValue(Discipline::class.java)
                    discipline?.let { disciplineList.add(it) }
                }
                disciplineAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Falha ao carregar disciplinas", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onItemClick(discipline: Discipline) {
        val intent = Intent(context, EditDisciplineFragment::class.java)
        intent.putExtra("DISCIPLINE_ID", discipline.id)
        startActivity(intent)
    }


    override fun onDeleteClick(discipline: Discipline) {
        database.child(discipline.id).removeValue()
        Toast.makeText(context, "Disciplina excluída", Toast.LENGTH_SHORT).show()
    }
}
