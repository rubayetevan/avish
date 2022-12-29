package com.avish.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.avish.admin.common.utility.Session
import com.avish.admin.databinding.ActivityMainBinding
import com.avish.admin.models.SessionData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var session: Session<SessionData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val destination = if (!session.isLoggedIn()) R.id.homeFragment else R.id.loginFragment
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.login_nav_graph)
        navGraph.setStartDestination(destination)
        navController.graph = navGraph

    }
}