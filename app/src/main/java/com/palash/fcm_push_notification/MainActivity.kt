package com.palash.fcm_push_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.palash.fcm_push_notification.fragments.SecondFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            /*val fragmentName = intent.getStringExtra("openFragment")
            if (fragmentName == "SpecificFragment") {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, SecondFragment())
                    .commit()

                //findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
                Log.d("MyData", "Here this data")
            }*/
        if (intent.hasExtra("fragment")) {
            val fragmentName = intent.getStringExtra("fragment")
            if (fragmentName == "specificFragment") {
                openSpecificFragment()
            }
        }
    }
    private fun openSpecificFragment() {
        // Replace fragment_container with the specific fragment
        val fragment = SecondFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}