import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.srp.R

class fragment2 : Fragment() {

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var completedRecyclerView: RecyclerView
    private lateinit var completedAdapter: CompletedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)

        completedRecyclerView = view.findViewById(R.id.completedRecyclerView)
        completedAdapter = CompletedAdapter()

        completedRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = completedAdapter
        }

        // Initialize the shared TodoViewModel
        todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)

        // Observe changes in the completed list
        todoViewModel.completedList.observe(viewLifecycleOwner, Observer { completedList ->
            // Update the RecyclerView adapter
            completedAdapter.submitList(completedList)
        })

        return view
    }
}
