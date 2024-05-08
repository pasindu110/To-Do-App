    package net.penguincoders.doit

    import android.content.DialogInterface
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.ItemTouchHelper
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.todoapp.R
    import com.google.android.material.floatingactionbutton.FloatingActionButton
    import net.penguincoders.doit.Adapters.ToDoAdapter
    import net.penguincoders.doit.Model.ToDoModel
    import net.penguincoders.doit.Utils.DatabaseHandler

    class MainActivity : AppCompatActivity(), DialogCloseListener {
        private lateinit var db: DatabaseHandler
        private lateinit var tasksRecyclerView: RecyclerView
        private lateinit var tasksAdapter: ToDoAdapter
        private lateinit var fab: FloatingActionButton
        private var taskList: List<ToDoModel> = ArrayList()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            supportActionBar?.hide()

            db = DatabaseHandler(this)
            db.openDatabase()

            tasksRecyclerView = findViewById(R.id.tasksRecyclerView)
            tasksRecyclerView.layoutManager = LinearLayoutManager(this)

            // Ensure that the constructor arguments match the ToDoAdapter's constructor
            tasksAdapter = ToDoAdapter(db, this)

            tasksRecyclerView.adapter = tasksAdapter

            val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(tasksAdapter))
            itemTouchHelper.attachToRecyclerView(tasksRecyclerView)

            fab = findViewById(R.id.fab)

            taskList = db.getAllTasks()
            taskList = taskList.reversed()

            tasksAdapter.setTasks(taskList)

            fab.setOnClickListener {
                AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
            }
        }

        override fun handleDialogClose(dialog: DialogInterface) {
            taskList = db.getAllTasks()
            taskList = taskList.reversed()
            tasksAdapter.setTasks(taskList)
            tasksAdapter.notifyDataSetChanged()
        }
    }
