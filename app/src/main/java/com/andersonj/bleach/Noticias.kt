package com.andersonj.bleach

import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Noticias : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noticiasAdapter: noticiasAdapter
    private var newsList = mutableListOf<NewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias) // Reemplaza con tu layout

        recyclerView = findViewById(R.id.recyclerViewNews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        noticiasAdapter = noticiasAdapter(newsList)
        recyclerView.adapter = noticiasAdapter
        fetchNews()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun fetchNews() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://remolacha.net/wp-json/wp/v2/posts?search=digeset"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 0 until response.length()) {
                    try {
                        val item = response.getJSONObject(i)
                        val title = item.getJSONObject("title").getString("rendered")
                        val content = item.getJSONObject("content").getString("rendered")
                        val newsItem = NewsItem(title, content)
                        newsList.add(newsItem)
                    } catch (e: JSONException) {
                        Log.e("JSON Parsing Error", e.toString())
                    }
                }
                newsList.reverse() // Revertir la lista aquí
                noticiasAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Log.e("Volley Error", error.toString())
            })

        queue.add(request)
    }

    fun formatText(text: String, style: Int): SpannableStringBuilder {
        val spannable = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY) as SpannableStringBuilder
        } else {
            SpannableStringBuilder.valueOf(Html.fromHtml(text))
        }
        spannable.setSpan(StyleSpan(style), 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
