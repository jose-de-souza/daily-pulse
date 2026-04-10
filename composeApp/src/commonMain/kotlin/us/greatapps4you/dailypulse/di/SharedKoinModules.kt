package us.greatapps4you.dailypulse.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import us.greatapps4you.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
    databaseModule,
    platformModule
)

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedKoinModules)
    }
}