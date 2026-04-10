package us.greatapps4you.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService) {
    suspend fun getArticles(): List<ArticleRaw> {
        val storedArticles = dataSource.getAllArticles()

        if(storedArticles.isEmpty()) {
            val fetchedArticles= service.fetchArticles()
            dataSource.inseretArticles(fetchedArticles)
            return fetchedArticles
        }
        return storedArticles
    }

}