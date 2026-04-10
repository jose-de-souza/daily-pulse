package us.greatapps4you.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import us.greatapps4you.dailypulse.BuildKonfig

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "technology"
    private val apiKey = BuildKonfig.NEWS_API_KEY

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }

}