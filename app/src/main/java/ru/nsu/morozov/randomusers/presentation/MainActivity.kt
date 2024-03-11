package ru.nsu.morozov.randomusers.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nsu.morozov.randomusers.R
import ru.nsu.morozov.randomusers.presentation.app.RandomUsersApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }
}