package ru.nsu.morozov.randomusers.presentation.app

import android.app.Application
import ru.nsu.morozov.randomusers.data.local.UsersDatabase
import ru.nsu.morozov.randomusers.di.DaggerAppComponent


class RandomUsersApplication: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(
            this@RandomUsersApplication,
            UsersDatabase.getDatabase(this)
        )
    }
}