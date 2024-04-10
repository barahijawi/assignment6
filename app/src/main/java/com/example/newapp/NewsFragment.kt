package com.example.newapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.databinding.NewsFragmentBinding

class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter(getNewsData())
        binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        binding.fabAddNews.setOnClickListener {
            // Implement action to add new news
        }
    }

    private fun getNewsData(): List<NewsItem> {
        val newsList = mutableListOf<NewsItem>()

        // Add some sample news items
        newsList.add(
            NewsItem(
                title = "New Sports Event Announced",
                description = "A new sports event has been announced for this summer, featuring various outdoor activities.",
                imageUrl = "https://example.com/image1.jpg"
            )
        )

        newsList.add(
            NewsItem(
                title = "Athlete Wins Championship",
                description = "The renowned athlete Jane Doe has won the championship in the latest competition.",
                imageUrl = "https://example.com/image2.jpg"
            )
        )

        newsList.add(
            NewsItem(
                title = "Historical Sports Archives Opened",
                description = "A new exhibition showcasing historical sports archives has been opened to the public.",
                imageUrl = "https://example.com/image3.jpg"
            )
        )

        return newsList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
