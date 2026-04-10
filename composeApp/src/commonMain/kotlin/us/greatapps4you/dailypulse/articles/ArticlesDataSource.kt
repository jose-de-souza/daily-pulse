package us.greatapps4you.dailypulse.articles

import us.greatapps4you.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles() : List<ArticleRaw> = database.dailyPulseDatabaseQueries
        .selectAllArticles(::mapToArticleRaw)
        .executeAsList()

    fun inseretArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                intertArticle(articleRaw)
            }
        }
    }

    fun clearArticles() =  database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun intertArticle(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ) : ArticleRaw = ArticleRaw(
        title,
        desc,
        date,
        url
    )
}