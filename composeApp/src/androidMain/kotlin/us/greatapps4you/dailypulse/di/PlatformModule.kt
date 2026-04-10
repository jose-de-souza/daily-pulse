package us.greatapps4you.dailypulse.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import us.greatapps4you.dailypulse.db.DatabaseDriverFactory

actual val platformModule: Module = module {
    single { DatabaseDriverFactory(androidContext()) }
}