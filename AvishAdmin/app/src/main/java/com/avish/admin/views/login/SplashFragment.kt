package com.avish.admin.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.avish.admin.R
import com.avish.admin.databinding.FragmentSplashBinding
import com.avish.admin.viewModel.AuthViewModel
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        //binding.lifecycleOwner = viewLifecycleOwner
        //binding.splyzaViewModel = splyzaViewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.getUserLoginStatus()
        lifecycleScope.launch {
            val authState = authViewModel.uiState.value
            if (authState.isUserLoggedIn) {
                findNavController().navigate(R.id.homeFragment)
            } else {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}