package com.avish.admin.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avish.admin.R
import com.avish.admin.databinding.FragmentHomeBinding
import com.avish.admin.databinding.FragmentLoginBinding
import com.avish.admin.databinding.FragmentProductListBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //binding.lifecycleOwner = viewLifecycleOwner
        //binding.splyzaViewModel = splyzaViewModel
        val view = binding.root
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}