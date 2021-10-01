package com.yunuserbek.eticaretbtk.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yunuserbek.eticaretbtk.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController =findNavController(this, R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
    }
}