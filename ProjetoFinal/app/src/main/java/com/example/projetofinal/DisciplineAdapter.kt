package com.example.projetofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisciplineAdapter(
    private val disciplineList: List<Discipline>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DisciplineAdapter.DisciplineViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(discipline: Discipline)
        fun onDeleteClick(discipline: Discipline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discipline, parent, false)
        return DisciplineViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisciplineViewHolder, position: Int) {
        val discipline = disciplineList[position]
        holder.bind(discipline)
    }

    override fun getItemCount() = disciplineList.size

    inner class DisciplineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val teacherTextView: TextView = itemView.findViewById(R.id.teacherTextView)
        private val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(discipline: Discipline) {
            nameTextView.text = discipline.name
            teacherTextView.text = discipline.teacher
            dayTextView.text = discipline.day
            timeTextView.text = discipline.time

            itemView.setOnClickListener {
                listener.onItemClick(discipline)
            }
            deleteButton.setOnClickListener {
                listener.onDeleteClick(discipline)
            }
        }
    }
}
