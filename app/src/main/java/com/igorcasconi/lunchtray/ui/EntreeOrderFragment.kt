package com.igorcasconi.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.igorcasconi.lunchtray.R
import com.igorcasconi.lunchtray.databinding.FragmentEntreeOrderBinding
import com.igorcasconi.lunchtray.model.LunchViewModel

class EntreeOrderFragment : Fragment() {

    private var _binding: FragmentEntreeOrderBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: LunchViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntreeOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            entreeOrderFragment = this@EntreeOrderFragment
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_entreeOrderFragment_to_sideDish)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_entreeOrderFragment_to_startOrder)
    }

}