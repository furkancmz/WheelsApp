package com.example.wheelsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheelsapp.SeriesRepository.getOnAirSeries
import com.example.wheelsapp.SeriesRepository.getPopularSeries
import com.example.wheelsapp.SeriesRepository.getTopRatedSeries
import com.google.android.material.bottomnavigation.BottomNavigationView

class SeriesActivity : AppCompatActivity() {
    private lateinit var popularSeries: RecyclerView
    private lateinit var popularSeriesAdapter: SeriesAdapter
    private lateinit var popularSeriesLayoutMgr: LinearLayoutManager
    private var popularSeriesPage = 1

    private lateinit var topRatedSeries: RecyclerView
    private lateinit var topRatedSeriesAdapter: SeriesAdapterSmall
    private lateinit var topRatedSeriesLayoutMgr: LinearLayoutManager
    private var topRatedSeriesPage = 1

    private lateinit var onTheAirSeries: RecyclerView
    private lateinit var onTheAirSeriesAdapter: SeriesAdapterSmall
    private lateinit var onTheAirSeriesMgr: LinearLayoutManager
    private var onTheAirSeriesPage = 1

    private lateinit var airingTodaySeries: RecyclerView
    private lateinit var airingTodaySeriesAdapter: SeriesAdapterSmall
    private lateinit var airingTodaySeriesMgr: LinearLayoutManager
    private var airingTodaySeriesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{ item ->
            when (item.itemId) {
                R.id.Movies -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Series -> {

                    return@OnNavigationItemSelectedListener true
                }
                 R.id.Explore -> {
                        val intent1 = Intent(this,ExploreActivity::class.java)
                     startActivity(intent1)
                     return@OnNavigationItemSelectedListener true
                 }
                 R.id.Notifications -> {
                     val intent2 = Intent(this,NotificationsActivity::class.java)
                     startActivity(intent2)
                     return@OnNavigationItemSelectedListener true
                 }
            }
            return@OnNavigationItemSelectedListener true
        }
        popularSeries = findViewById(R.id.popular_series)
        popularSeriesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularSeries.layoutManager = popularSeriesLayoutMgr
        popularSeriesAdapter =
            SeriesAdapter(mutableListOf()) { serie ->
                showSerieDetails(serie)
            }
        popularSeries.adapter = popularSeriesAdapter
        getPopularSeries()

        topRatedSeries = findViewById(R.id.top_rated_series)
        topRatedSeriesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedSeries.layoutManager = topRatedSeriesLayoutMgr
        topRatedSeriesAdapter =
            SeriesAdapterSmall(mutableListOf()) { serie ->
                showSerieDetails(serie)
            }
        topRatedSeries.adapter = topRatedSeriesAdapter
        getTopRatedSeries()

        onTheAirSeries=findViewById(R.id.on_the_air_series)
        onTheAirSeriesMgr= LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        onTheAirSeries.layoutManager = onTheAirSeriesMgr
        onTheAirSeriesAdapter =
            SeriesAdapterSmall(mutableListOf()) { serie ->
                showSerieDetails(serie)
            }
        onTheAirSeries.adapter = onTheAirSeriesAdapter
        getOnTheAirSeries()

        airingTodaySeries = findViewById(R.id.airing_today_series)
        airingTodaySeriesMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        airingTodaySeries.layoutManager = airingTodaySeriesMgr
        airingTodaySeriesAdapter =
            SeriesAdapterSmall(mutableListOf()) { serie ->
                showSerieDetails(serie)
            }
        airingTodaySeries.adapter = airingTodaySeriesAdapter
        getAiringTodaySeries()
    }
    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_LONG).show()
    }
    private fun getPopularSeries() {
        SeriesRepository.getPopularSeries(
            popularSeriesPage,
            ::onPopularSeriesFetched,
            ::onError
        )
    }
    private fun attachPopularSeriesOnScrollListener() {
        popularSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularSeriesLayoutMgr.itemCount
                val visibleItemCount = popularSeriesLayoutMgr.childCount
                val firstVisibleItem = popularSeriesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularSeries.removeOnScrollListener(this)
                    popularSeriesPage++
                    getPopularSeries()
                }
            }
        })
    }
    private fun onPopularSeriesFetched(series: List<Serie>) {
        popularSeriesAdapter.appendSeries(series)
        attachPopularSeriesOnScrollListener()
    }
    private fun getTopRatedSeries() {
        SeriesRepository.getTopRatedSeries(
            topRatedSeriesPage,
            ::onTopRatedSeriesFetched,
            ::onError
        )
    }
    private fun attachTopRatedSeriesOnScrollListener() {
        topRatedSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedSeriesLayoutMgr.itemCount
                val visibleItemCount = topRatedSeriesLayoutMgr.childCount
                val firstVisibleItem = topRatedSeriesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedSeries.removeOnScrollListener(this)
                    topRatedSeriesPage++
                    getTopRatedSeries()
                }
            }
        })
    }
    private fun onTopRatedSeriesFetched(series: List<Serie>) {
        topRatedSeriesAdapter.appendSeries(series)
        attachTopRatedSeriesOnScrollListener()
    }
    private fun getOnTheAirSeries() {
        SeriesRepository.getOnAirSeries(
            onTheAirSeriesPage,
            ::onOnTheAirSeriesFetched,
            ::onError
        )
    }
    private fun attachOnTheAirSeriesOnScrollListener() {
        onTheAirSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onTheAirSeriesMgr.itemCount
                val visibleItemCount = onTheAirSeriesMgr.childCount
                val firstVisibleItem = onTheAirSeriesMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onTheAirSeries.removeOnScrollListener(this)
                    onTheAirSeriesPage++
                    getOnTheAirSeries()
                }
            }
        })
    }
    private fun onOnTheAirSeriesFetched(series: List<Serie>) {
        onTheAirSeriesAdapter.appendSeries(series)
        attachOnTheAirSeriesOnScrollListener()
    }

    private fun getAiringTodaySeries() {
        SeriesRepository.getAiringTodaySeries(
            airingTodaySeriesPage,
            ::onAiringTodaySeriesFetched,
            ::onError
        )
    }
    private fun attachAiringTodaySeriesOnScrollListener() {
        airingTodaySeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = airingTodaySeriesMgr.itemCount
                val visibleItemCount = airingTodaySeriesMgr.childCount
                val firstVisibleItem = airingTodaySeriesMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    airingTodaySeries.removeOnScrollListener(this)
                    airingTodaySeriesPage++
                    getAiringTodaySeries()
                }
            }
        })
    }
    private fun onAiringTodaySeriesFetched(series: List<Serie>) {
        airingTodaySeriesAdapter.appendSeries(series)
        attachAiringTodaySeriesOnScrollListener()
    }
    private fun showSerieDetails(serie:Serie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)

        intent.putExtra(MOVIE_BACKDROP, serie.backdropPath)
        intent.putExtra(MOVIE_POSTER, serie.posterPath)
        intent.putExtra(MOVIE_TITLE, serie.title)
        intent.putExtra(MOVIE_RATING, serie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, serie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, serie.overview)

        startActivity(intent)
    }
}