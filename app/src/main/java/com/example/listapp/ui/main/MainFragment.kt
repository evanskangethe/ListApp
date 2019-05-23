package com.example.listapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.R
import com.example.listapp.adapter.TaskListAdapter
import com.example.listapp.extension.toast
import kotlinx.android.synthetic.main.main_fragment.*




class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewManager = LinearLayoutManager(context)

       val mAdapter = TaskListAdapter{
           context?.toast("${it.title}")
           viewModel.delete(it)
           //context?.toast("Task Deleted")

       }

        recyclerView =recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        viewModel.getAllTasks().observe(this, Observer { tasks ->
            tasks?.let {
                mAdapter.setTask(tasks)
            }
        })

        viewModel.getAllTasks().observe(this, Observer { tasks ->
            tasks?.let {
                mAdapter.submitList(it)
            }
        })
    }

}
