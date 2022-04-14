package com.example.leaguetable.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguetable.R
import com.example.leaguetable.adapters.TableAdapter
import com.example.leaguetable.databinding.FragmentTableBinding
import com.example.leaguetable.ui.FootballViewModel
import com.example.leaguetable.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment(R.layout.fragment_table) {
    lateinit var binding: FragmentTableBinding
    private val viewModel: FootballViewModel by viewModels()
    lateinit var tableAdapter: TableAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTableBinding.bind(view)

        setupRecyclerView()

        binding.spLeagueNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.getItemAtPosition(position)) {
                    "Premier League" -> viewModel.getLeagueTable("4328")
                    "Bundesliga" -> viewModel.getLeagueTable("4331")
                    "Serie A" -> viewModel.getLeagueTable("4332")
                    "La Liga" -> viewModel.getLeagueTable("4335")
                    "French League" -> viewModel.getLeagueTable("4334")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        viewModel.tables.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { tableResponse ->
                        tableAdapter.differ.submitList(tableResponse.team.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("TableFragment", "error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun setupRecyclerView() {
        tableAdapter = TableAdapter()
        binding.tableRecycler.apply {
            adapter = tableAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}