package com.example.projetofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth
// chave api google maps: AIzaSyAnB0q2f9x-zT6Ug0PN-P5XmPHKQrWiv1U
//AIzaSyAnB0q2f9x-zT6Ug0PN-P5XmPHKQrWiv1U
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}
