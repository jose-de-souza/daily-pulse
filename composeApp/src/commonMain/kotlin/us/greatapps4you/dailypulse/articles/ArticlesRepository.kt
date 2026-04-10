package us.greatapps4you.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if(forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }
        val storedArticles = dataSource.getAllArticles()

        if(storedArticles.isEmpty()) {
            return fetchArticles()
        }
        return storedArticles
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.inseretArticles(fetchedArticles)
        return fetchedArticles
    }

}