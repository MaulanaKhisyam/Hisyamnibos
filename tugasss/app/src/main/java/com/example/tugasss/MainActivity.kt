import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tugasss.network.ApiClient
import com.example.tugasss.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import com.example.tugasss.network.Character
import com.google.android.material.snackbar.Snackbar
import com.bumptech.glide.Glide
import com.example.tugasss.MainAdapter
import com.example.tugasss.MainViewModel
import com.example.tugasss.R
import com.example.tugasss.ScreenState


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    // Memanggil "onCreate" pada AppCompatActivity untuk
    // memastikan bahwa kode dalam onCreate sudah dijalankan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur layout untuk menampilkan konten pada aplikasi
        setContentView(R.layout.activity_main)

        viewModel.characterLiveData.observe(this, { state->
            processCharacterResponse(state)
        })
    }

    private fun processCharacterResponse(state: ScreenState<List<Character>?>) {

        val pb = findViewById<ProgressBar>(R.id.progressBar)

        when(state){
            is ScreenState.Loading ->{
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success ->{
                pb.visibility = View.GONE
                if(state.data != null){
                    val adapter = MainAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.characterRV)
                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adapter


                }
            }
            is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view,state.massage!!,Snackbar.LENGTH_LONG).show()
            }
        }





























//        // Membuat inisiasi untuk mendapatkan dafatar data dari API yang dipakai
//        val client = ApiClient.apiService.fetchCharacters("1")
//
//        // client.qnqueque untuk mengatur permintaan singkorinisasi ke Api
//        // yang diteruskan ke object untuk mengatasi respon dari permintaan
//        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
//
//            // kode dipanggil jika perintah ke API berhasil menerima respon yang valid
//            override fun onResponse(
//                call: Call<CharacterResponse>,
//                response: Response<CharacterResponse>
//            ) {
//                // kondisi untuk mencheck respon berhasil
//                if (response.isSuccessful) {
//                    Log.d("character", "" + response.body())
//
//                    // untuk mecheck apakah result tidak null
//                    val result= response.body()?.result
//                    result?.let{
//                        // variabel untuk menerima data karakter
//                        val adapter = MainAdapter(result)
//                        // inisialisasi
//                        val recyclerView = findViewById<RecyclerView>(R.id.characterRV)
//                        // untuk mengatur layout
//                        recyclerView?.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//                        // mengatur adapter yang telah dibuat ke recyclerView
//                        recyclerView?.adapter = adapter
//                    }
//                }
//            }
//            //  untuk mencetak pesan failed
//                override  fun  onFailure(call: Call<CharacterResponse>, t: Throwable) {
//                Log.e("failed", ""+t.message)
//            }
//        })
    }
}