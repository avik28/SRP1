import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.srp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fragment1 : Fragment() {

    private lateinit var addButton: FloatingActionButton
    private lateinit var linearLayout: LinearLayout
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)

        addButton = view.findViewById(R.id.floatingActionButton)
        linearLayout = view.findViewById(R.id.linearLayout)

        // Initialize the shared TodoViewModel
        todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)

        addButton.setOnClickListener {
            showAddTaskDialog()
        }

        // Observe changes in the todo list
        todoViewModel.todoList.observe(viewLifecycleOwner, { todoList ->
            // Update UI with new todo list here if necessary
            // This will refresh the UI if the todo list changes (e.g., task added from Fragment2)
            updateUI(todoList)
        })

        return view
    }

    private fun showAddTaskDialog() {
        val input = EditText(requireContext()).apply {
            hint = "Enter text for the task"
        }
        AlertDialog.Builder(requireContext()).apply {
            setTitle("New Task")
            setView(input)
            setPositiveButton("Add") { dialog, _ ->
                val taskText = input.text.toString()
                if (taskText.isNotEmpty()) {
                    addNewTask(taskText)
                } else {
                    Toast.makeText(requireContext(), "Cannot add empty task", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    private fun addNewTask(taskText: String) {
        val newCheckBox = CheckBox(requireContext()).apply {
            text = taskText
            id = View.generateViewId()
            // Set a listener for checkbox changes
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // Remove the task from the todo list
                    val newList = todoViewModel.todoList.value?.toMutableList() ?: mutableListOf()
                    newList.remove(taskText)
                    todoViewModel.setTodoList(newList)

                    // Add the task to the completed list
                    val completedList = todoViewModel.completedList.value?.toMutableList() ?: mutableListOf()
                    completedList.add(taskText)
                    todoViewModel.setCompletedList(completedList)
                }
            }
        }
        linearLayout.addView(newCheckBox)
        // Add the task to the todo list
        val newList = todoViewModel.todoList.value?.toMutableList() ?: mutableListOf()
        newList.add(taskText)
        todoViewModel.setTodoList(newList)
    }

    private fun updateUI(todoList: List<String>) {
        // Remove all views from the linear layout
        linearLayout.removeAllViews()

        // Add each task from the todo list to the linear layout
        for (taskText in todoList) {
            val newCheckBox = CheckBox(requireContext()).apply {
                text = taskText
                id = View.generateViewId()
                // Set a listener for checkbox changes
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // Remove the task from the todo list
                        val newList = todoViewModel.todoList.value?.toMutableList() ?: mutableListOf()
                        newList.remove(taskText)
                        todoViewModel.setTodoList(newList)

                        // Add the task to the completed list
                        val completedList = todoViewModel.completedList.value?.toMutableList() ?: mutableListOf()
                        completedList.add(taskText)
                        todoViewModel.setCompletedList(completedList)
                    }
                }
            }
            linearLayout.addView(newCheckBox)
        }
    }

}
