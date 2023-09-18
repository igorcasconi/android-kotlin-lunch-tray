package com.igorcasconi.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.igorcasconi.lunchtray.R
import com.igorcasconi.lunchtray.databinding.FragmentStartOrderBinding

class StartOrder : Fragment() {

    private var binding: FragmentStartOrderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentStartOrderBinding = FragmentStartOrderBinding.inflate(inflater, container, false)
        binding = fragmentStartOrderBinding
        return fragmentStartOrderBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startOrder = this
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_startOrder_to_entreeOrderFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

