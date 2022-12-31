package com.avish.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.avish.admin.common.utility.Resource
import com.avish.admin.common.utility.hide
import com.avish.admin.common.utility.show
import com.avish.admin.databinding.ActivityMainBinding
import com.avish.admin.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        lifecycleScope.launch {
            authViewModel.isUserLoggedIn().collect {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.hide()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
                        val navController = navHostFragment.navController
                        val destination =
                            if (it.data == true) R.id.homeFragment else R.id.loginFragment
                        val graphInflater = navController.navInflater
                        val navGraph = graphInflater.inflate(R.navigation.login_nav_graph)
                        navGraph.setStartDestination(destination)
                        navController.graph = navGraph
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Empty -> {

                    }
                    is Resource.Loading -> {
                        binding.progressBar.show()
                    }
                }
            }
        }

    }
}