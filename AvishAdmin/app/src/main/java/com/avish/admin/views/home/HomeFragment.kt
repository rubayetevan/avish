package com.avish.admin.views.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.avish.admin.R
import com.avish.admin.common.utility.session.Session
import com.avish.admin.databinding.FragmentHomeBinding
import com.avish.admin.models.SessionData
import javax.inject.Inject


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val host: NavHostFragment = childFragmentManager
            .findFragmentById(R.id.bottom_nav_host_fragment) as NavHostFragment?
            ?: return
        binding.bottomNavigationView.setupWithNavController(host.navController)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}