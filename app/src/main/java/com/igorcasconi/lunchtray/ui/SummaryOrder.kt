package com.igorcasconi.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.igorcasconi.lunchtray.R
import com.igorcasconi.lunchtray.databinding.FragmentSummaryOrderBinding
import com.igorcasconi.lunchtray.model.LunchViewModel

class SummaryOrder : Fragment() {

    private var _binding: FragmentSummaryOrderBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: LunchViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryOrderFragment = this@SummaryOrder
        }
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryOrder_to_startOrder2)
    }

    fun finishOrder() {
        sharedViewModel.resetOrder()
        val contextView = binding.root
        Snackbar.make(contextView, R.string.success_order, Snackbar.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_summaryOrder_to_startOrder2)
    }

}