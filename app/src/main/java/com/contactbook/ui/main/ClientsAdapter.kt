package com.contactbook.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contactbook.R
import com.contactbook.data.model.ClientModel
import com.contactbook.data.model.SystemOfMeasure
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
            val weightValue = "${item.weight} ${getUnitOfMeasure(item.systemOfMeasure)}"
            binding.weight.text = weightValue
            binding.dob.text = item.dateOfBirth.toString()
            Glide
                .with(binding.photo.context)
                .load(item.photo)
                .centerCrop()
                .placeholder(R.drawable.portrait_placeholder)
                .into(binding.photo)
        }

        private fun getUnitOfMeasure(system: SystemOfMeasure): String {
            return if (system == SystemOfMeasure.METRIC) {
                binding.weight.context.getString(R.string.kilogram)
            } else {
                binding.weight.context.getString(R.string.pound)
            }
        }
    }
}

object ClientsDiffUtil : DiffUtil.ItemCallback<ClientModel>() {
    override fun areItemsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem.weight == newItem.weight && oldItem.dateOfBirth == newItem.dateOfBirth

    override fun areContentsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem == newItem
}