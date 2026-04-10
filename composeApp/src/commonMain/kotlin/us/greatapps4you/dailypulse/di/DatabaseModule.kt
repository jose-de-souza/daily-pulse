package us.greatapps4you.dailypulse.di

import org.koin.core.module.Module
import org.koin.dsl.module
import us.greatapps4you.dailypulse.db.DailyPulseDatabase
import us.greatapps4you.dailypulse.db.DatabaseDriverFactory

expect val platformModule: Module

val databaseModule = module {
    single {
        val factory = get<DatabaseDriverFactory>()
        DailyPulseDatabase(factory.createDriver())
    }
}