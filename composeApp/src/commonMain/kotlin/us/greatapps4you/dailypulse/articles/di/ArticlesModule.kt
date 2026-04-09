package us.greatapps4you.dailypulse.articles.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import us.greatapps4you.dailypulse.articles.ArticlesService
import us.greatapps4you.dailypulse.articles.ArticlesUseCase
import us.greatapps4you.dailypulse.articles.ArticlesViewModel

val articlesModule = module {
    // Standard dependencies live as Singletons
    singleOf(::ArticlesService)
    singleOf(::ArticlesUseCase)

    // ViewModels use the specific viewModel DSL!
    viewModelOf(::ArticlesViewModel)
}