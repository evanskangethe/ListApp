package com.example.listapp.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.listapp.R
import com.example.listapp.extension.inflate
import com.example.listapp.holder.TaskViewHolder
import com.example.listapp.model.Task

class TaskListAdapter(private val clickListener: (Task) -> Unit):
    ListAdapter<Task,TaskViewHolder>(TaskDiffCallback()){

    var tasks = emptyList<Task>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflatedView = parent.inflate(R.layout.item_task_row,false)
        return TaskViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    internal fun setTask(task: List<Task>) {
        this.tasks = task
        notifyDataSetChanged()
    }
}