package us.greatapps4you.dailypulse // Inside androidMain

import android.app.Application
import org.koin.android.ext.koin.androidContext
import us.greatapps4you.dailypulse.di.initKoin

class DailyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            // This is why the optional Koin config block is there in commonMain
            androidContext(this@DailyPulseApp)
        }
    }
}