import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.srp.R

class CompletedAdapter : ListAdapter<String, CompletedAdapter.CompletedViewHolder>(CompletedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_completed, parent, false)
        return CompletedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompletedViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class CompletedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.completedItemTextView)

        fun bind(completedItem: String) {
            textView.text = completedItem
        }
    }

    class CompletedDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
