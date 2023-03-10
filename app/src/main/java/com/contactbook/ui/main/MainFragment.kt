package com.contactbook.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.contactbook.R
import com.contactbook.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            viewModel.newClient()
            findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }

        val editObserver = Observer<Boolean> {
            findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }
        viewModel.editClient.observe(viewLifecycleOwner, editObserver)

        val clientAdapter = ClientsAdapter(viewModel)
        viewModel.clientList
            .onEach {
                updateUI(it.isEmpty())
                clientAdapter.submitList(it)
                clientAdapter.notifyDataSetChanged()
            }.catch {
                //TODO: write log message
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        val dividerItemDecoration = DividerItemDecoration(
            binding.clientsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        ResourcesCompat.getDrawable(resources, R.drawable.list_divider, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        with(binding.clientsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = clientAdapter
            addItemDecoration(dividerItemDecoration)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchClientsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(isListEmpty: Boolean) {
        if(isListEmpty) {
            binding.clientsRecyclerView.visibility = View.GONE
            binding.message.visibility = View.VISIBLE
        } else {
            binding.clientsRecyclerView.visibility = View.VISIBLE
            binding.message.visibility = View.GONE
        }
    }
}