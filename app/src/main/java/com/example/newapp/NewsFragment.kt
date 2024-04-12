package com.example.newapp

import NewsAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.databinding.NewsFragmentBinding
import com.google.android.material.textfield.TextInputEditText

class NewsFragment : Fragment() {
    private val newsList = mutableListOf<NewsItem>()
    private val newsAdapter by lazy { NewsAdapter(mutableListOf()) }
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
            showAddNewsDialog()

        }
    }
    private fun showAddNewsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_news_dialog, null)

        val imageEditText = dialogView.findViewById<TextInputEditText>(R.id.newsImageEditText)
        val titleEditText = dialogView.findViewById<TextInputEditText>(R.id.newsTitleEditText)
        val descriptionEditText = dialogView.findViewById<TextInputEditText>(R.id.newsDescriptionEditText)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.addNewsButton).setOnClickListener {
            val newsImage = imageEditText.text.toString().trim()
            val newsTitle = titleEditText.text.toString().trim()
            val newsDescription = descriptionEditText.text.toString().trim()

            if (newsImage.isNotBlank() && newsTitle.isNotBlank() && newsDescription.isNotBlank()) {
                val newNewsItem = NewsItem(newsTitle, newsDescription, newsImage)
                addNewsItem(newNewsItem)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun addNewsItem(newsItem: NewsItem) {
        newsList.add(newsItem) // Add new item to the top of the list
//        newsAdapter.notifyItemInserted() // Notify adapter of new item at position 0
        binding.recyclerViewNews.scrollToPosition(0) // Scroll to top to show the newly added item
    }

    private fun getNewsData(): List<NewsItem> {


        // Add some sample news items
        newsList.add(
            NewsItem(
                title = "Fighting ibexes and a ballet rehearsal: photos of the day – Tuesday",
                description = " Dancers perform during an Australian Ballet dress rehearsal of Carmen at the Sydney Opera House in Australia. Photograph: Bianca de Marchi/AAP",
                imageUrl = "https://picsum.photos/500/300"
            )
        )

        newsList.add(
            NewsItem(
                title = "A Seoul rally and a solar eclipse: photos of the day – Mondayp",
                description = "Campaigners for the ruling People Power party dance during a rally in Seoul, South Korea, before parliamentary elections. Photograph: Anthony Wallace/AFP/Getty Images",
                imageUrl = "https://picsum.photos/500/300"
            )
        )

        newsList.add(
            NewsItem(
                title = "Eid al-Fitr and a rope climber: photos of the day – Wednesday",
                description = "day – Wednesday\n" +
                        " A Muslim man plays with a baby at the end of Eid al-Fitr prayers in Bucharest, Romania. Photograph: Andreea Alexandru/AP.",
                imageUrl = "https://picsum.photos/500/300"
            )
        )

        return newsList.asReversed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
