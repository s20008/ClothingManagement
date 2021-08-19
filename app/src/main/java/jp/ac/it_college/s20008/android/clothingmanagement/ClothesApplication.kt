package jp.ac.it_college.s20008.android.clothingmanagement

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class ClothesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(config)
    }
}