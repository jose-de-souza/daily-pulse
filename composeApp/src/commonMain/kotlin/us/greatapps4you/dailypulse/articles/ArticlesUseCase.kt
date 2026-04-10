package us.greatapps4you.dailypulse.articles

import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.math.abs
class ArticlesUseCase(private val repo: ArticlesRepository) {
    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw ->
        Article (
            raw.title,
            raw.desc ?: "Click to find out more",
            getDaysAgoString(raw.date),
            raw.imageUrl ?: "https://lh3.googleusercontent.com/gps-cs-s/AHVAwerLtUohWE4oQIshcDkQggtEL9XmuQS5k5-i6OTMhLIOTi-wySGwY3mGmADfk86K925LkkuWC4752qSWUl8kactBvU618TRz5VGWCdJLD_RRnGWsTASx7zQ4Y6CGy4xdNT187RTn=s1360-w1360-h1020"
        )
    }

    suspend fun getArticles(): List<Article> {
        val articlesRaw = repo.getArticles()
        return mapArticles(articlesRaw)
    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}