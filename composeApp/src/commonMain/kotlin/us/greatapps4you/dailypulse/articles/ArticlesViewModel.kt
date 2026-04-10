package us.greatapps4you.dailypulse.articles

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import us.greatapps4you.dailypulse.BaseViewModel

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    // Start in the loading state
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> = _articlesState.asStateFlow()

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        viewModelScope.launch {
            // Ensure we are in a loading state before starting (useful for pull-to-refresh later)
            _articlesState.emit(ArticlesState(loading = true, articles = _articlesState.value.articles))

            try {
                val fetchedArticles = useCase.getArticles(forceFetch)

                // Success: Turn off loading, pass the data, clear any previous errors
                _articlesState.emit(
                    ArticlesState(
                        articles = fetchedArticles,
                        loading = false,
                        error = null
                    )
                )
            } catch (e: Exception) {
                // Failure: Turn off loading, keep existing articles (if any), pass the error message
                _articlesState.emit(
                    ArticlesState(
                        articles = _articlesState.value.articles,
                        loading = false,
                        error = e.message ?: "An unknown error occurred"
                    )
                )
            }
        }
    }
}