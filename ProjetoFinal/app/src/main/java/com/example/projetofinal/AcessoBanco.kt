package com.example.projetofinal

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetofinal.databinding.AcessobancoBinding
import com.example.projetofinal.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class Message(val author: String, val content: String)
data class Msg(val uid: Int, val message: Message){

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "message" to message,
        )
    }
}

class AcessoBanco : AppCompatActivity() {
    private lateinit var binding : AcessobancoBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcessobancoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database = Firebase.database.reference



        binding.botao.setOnClickListener {
            val message = Message("John", "Hello, world!")
            // aqui cria uma entrada chamada messages
            // e cria um UUID para cada push realizado no banco.
            // message é uma classe que vira uma estrutura json
            database.child("messages").push().setValue(message)
        }

        binding.botao2.setOnClickListener {
            database.child("messages").get().addOnSuccessListener {it
                Log.i("firebase","${it.value}")
                val list : MutableList<Message> = mutableListOf()
                val children = it!!.children
                children.forEach {
                    Log.i("dd",it.child("author").getValue().toString())
                }

            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
        }
    }
}