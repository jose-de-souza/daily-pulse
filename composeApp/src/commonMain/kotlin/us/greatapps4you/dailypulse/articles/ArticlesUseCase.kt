package us.greatapps4you.dailypulse.articles

class ArticlesUseCase(private val service: ArticlesService) {
    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw ->
        Article (
            raw.title,
            raw.desc ?: "Click to find out more",
            raw.date,
            raw.imageUrl ?: "https://lh3.googleusercontent.com/gps-cs-s/AHVAwerLtUohWE4oQIshcDkQggtEL9XmuQS5k5-i6OTMhLIOTi-wySGwY3mGmADfk86K925LkkuWC4752qSWUl8kactBvU618TRz5VGWCdJLD_RRnGWsTASx7zQ4Y6CGy4xdNT187RTn=s1360-w1360-h1020"
        )
    }

    suspend fun getArticles(): List<Article> {
        val articlesRaw = service.fetchArticles()
        return mapArticles(articlesRaw)
    }
}