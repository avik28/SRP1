import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    // Mutable live data for the todo list
    private val _todoList = MutableLiveData<List<String>>()
    val todoList: LiveData<List<String>>
        get() = _todoList

    // Mutable live data for the completed list
    private val _completedList = MutableLiveData<List<String>>()
    val completedList: LiveData<List<String>>
        get() = _completedList

    init {
        // Initialize lists
        _todoList.value = mutableListOf()
        _completedList.value = mutableListOf()
    }

    // Function to set the todo list
    fun setTodoList(list: List<String>) {
        _todoList.value = list
    }

    // Function to set the completed list
    fun setCompletedList(list: List<String>) {
        _completedList.value = list
    }
}
