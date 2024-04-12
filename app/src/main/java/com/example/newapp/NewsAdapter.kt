import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newapp.NewsItem
import com.example.newapp.R

// NewsAdapter.kt
class NewsAdapter(private val newsList: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Initialize your views here
        val titleTextView: TextView = view.findViewById(R.id.newsTitleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.newsDescriptionTextView)
        val newsImageView: ImageView = view.findViewById(R.id.newsImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.titleTextView.text = newsItem.title
        holder.descriptionTextView.text = newsItem.description
        // Use Glide to load the image
        Glide.with(holder.itemView.context)
            .load(newsItem.imageUrl)
            .into(holder.newsImageView)
    }

    override fun getItemCount() = newsList.size
}
