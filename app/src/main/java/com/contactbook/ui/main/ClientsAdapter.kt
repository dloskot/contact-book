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
import java.time.format.DateTimeFormatter

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

            var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
            binding.dob.text = item.dateOfBirth.format(formatter)

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

        companion object {
            const val DATE_FORMAT = "MMMM dd, yyyy"
        }
    }
}

object ClientsDiffUtil : DiffUtil.ItemCallback<ClientModel>() {
    override fun areItemsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem.weight == newItem.weight && oldItem.dateOfBirth == newItem.dateOfBirth

    override fun areContentsTheSame(oldItem: ClientModel, newItem: ClientModel): Boolean = oldItem == newItem
}