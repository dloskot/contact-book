package com.contactbook.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.contactbook.data.model.ClientModel
import com.contactbook.databinding.ClientItemBinding

class ClientsAdapter : ListAdapter<ClientModel, ClientsAdapter.ClientsViewHolder>(ClientsDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(ClientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ClientsViewHolder(private val binding: ClientItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ClientModel) {
            binding.weight.text = item.weight.toString()
            binding.dob.text = item.dateOfBirth.toString()
        }
    }
}

object ClientsDiffUtil : DiffUtil.ItemCallback<ClientModel>() {
    override fun areItemsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem.weight == newItem.weight && oldItem.dateOfBirth == newItem.dateOfBirth

    override fun areContentsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem == newItem
}