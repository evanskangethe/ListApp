package com.example.listapp.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.Task
import kotlinx.android.synthetic.main.item_task_row.view.*


class TaskViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, clickListener: (Task) -> Unit) = with(itemView){
                itemView.task_title.text = task.title
                itemView.task_completed.isChecked = task.completed
                itemView.setOnClickListener {
                        clickListener(task)
                }
        }
}