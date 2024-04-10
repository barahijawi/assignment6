import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newapp.AboutMeFragment
import com.example.newapp.AthletesFragment
import com.example.newapp.EventsFragment
import com.example.newapp.HistoricalArchiveFragment
import com.example.newapp.NewsFragment
import com.example.newapp.SportsFragment

class MyViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val fragments = arrayOf(SportsFragment(), NewsFragment(), AthletesFragment(), EventsFragment(), HistoricalArchiveFragment(), AboutMeFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
