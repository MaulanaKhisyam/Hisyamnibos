package com.example.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.databinding.FragmentOverviewBinding

/**
 * Fragmnet yang menunjukan status layanan web foto mars
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    /**
     * Mengemabngkan tata letak dengan data binding, mengaktifkan data binding
     * untuk mengamti livedata dan mempersipkan recyclerview dengan adaptor
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Mengizinkan pengikat data  untuk mengamati livedata dengan siklus fragment
        binding.lifecycleOwner = this

        // Memberikan akses untuk OverviewViewModel
        binding.viewModel = viewModel

        // Menyetel adaptor PhotoGridAdapter
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}
