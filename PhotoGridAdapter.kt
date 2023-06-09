package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

/**
 * class untuk mengimplementasikan recyclerview, listadapter yang menggunakan data binding
 * untuk menampilkan list
 */
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * class MarsPhotosViewHolder yang mengambil variabel binding dari yang terkait
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // code untuk memaksa data untuk segera dieksekusi,
            // yang memungkinkan recyclearview membuat pengukuran tampilan yang benar
            binding.executePendingBindings()
        }
    }

    /**
     * mengizinkan recyclerview menentukan item mana yang telah berubah dari data yang diperbaharui
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * code untuk membuat tataletak tampilan
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Code untuk mengganti konten tampilan, yang dipanngil oleh pengelola tat letak
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
