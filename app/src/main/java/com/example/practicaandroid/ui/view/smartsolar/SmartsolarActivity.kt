package com.example.practicaandroid.ui.view.smartsolar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivitySmartsolarBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmartsolarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartsolarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartsolarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(
            binding.fragmentContainer.id,
            MiinstalacionFragment()
        ).commit()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> supportFragmentManager.beginTransaction().replace(
                        binding.fragmentContainer.id,
                        MiinstalacionFragment()
                    ).commit()

                    1 -> supportFragmentManager.beginTransaction().replace(
                        binding.fragmentContainer.id,
                        EnergiaFragment()
                    ).commit()

                    2 -> supportFragmentManager.beginTransaction().replace(
                        binding.fragmentContainer.id,
                        DetallesFragment()
                    ).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //Doesnt do anything
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //Doesnt do anything
            }
        })

        binding.toolbar.binding.iconoIzquierda.setOnClickListener {
            finish()
        }
    }
}