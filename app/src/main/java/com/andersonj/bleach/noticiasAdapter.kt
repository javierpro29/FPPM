package com.andersonj.bleach

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class noticiasAdapter(private val newsList: List<NewsItem>) :

    RecyclerView.Adapter<noticiasAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_noticias_adapter, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)

        fun bind(newsItem: NewsItem) {
            titleTextView.text = formatText(newsItem.title, Typeface.BOLD)
            contentTextView.text = formatTextFromHtml(newsItem.content, Typeface.NORMAL)

        }

        private fun formatTextFromHtml(html: String, style: Int): SpannableStringBuilder {
            val spannable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SpannableStringBuilder.valueOf(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY))
            } else {
                SpannableStringBuilder.valueOf(Html.fromHtml(html))
            }
            spannable.setSpan(StyleSpan(style), 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannable
        }
    }

    fun formatText(text: String, style: Int): SpannableStringBuilder {
        val spannable = SpannableStringBuilder(text)
        spannable.setSpan(StyleSpan(style), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }

}