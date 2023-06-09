package com.example.tugasss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.tugasss.network.Character


//mendeklarasikan class MainAdapter untuk menerima parameter yang akan ditampilkan dalam RecyclerView
class MainAdapter(private val charactersList: List<Character>?) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    // class MainViewHolder yang mewariskan dari kelas RecyclerView.ViewHolder
    // inner class untuk mengikat data dan menginisialisasi karakter
    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        // metode bindata yang menerima parameter character dari kelas Character
        // yang mengikat data karakter ke tampilan dengan mengakses UI menggunnkan findViewById
        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = character.name
            // untuk mengatur gambar karakter dengan menggunakan library Coil untuk URL
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }
    // Method dipanggil jika baru dibuat
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // layout yang diinflate menggunakan "LayoutInflater" yang dikembalikan sebagai inisiasi dari "MainViewHolder"
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_item, parent, false)
        return MainViewHolder(view)
    }
    // method untuk mengembalikan jumlah item jika null, maka akan mengembalikan ke 0
    override fun getItemCount(): Int {
        return charactersList?.size ?: 0
    }
    // method yang dipanggil jika karakter perlu di bind ke ViewHolder.
    // dan data karakter pada posisi tertentu dipanggil
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        charactersList?.get(position)?.let { character ->
            holder.bindData(character)
        }
    }
}

