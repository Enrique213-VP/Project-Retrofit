package google.android.svape.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import google.android.svape.project_retrofit.adapter.Adapter
import google.android.svape.project_retrofit.databinding.ActivityMainBinding
import google.android.svape.project_retrofit.model.APIService
import google.android.svape.project_retrofit.model.DogsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var dogImages = listOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchViewDogs.setOnQueryTextListener(this)
    }


    private fun initCharacter(puppies: DogsResponse){
        if(puppies.status == "success"){
            dogImages = puppies.images
        }
        adapter = Adapter(dogImages)
        binding.recyclerDogs.setHasFixedSize(true)
        binding.recyclerDogs.layoutManager = LinearLayoutManager(this)
        binding.recyclerDogs.adapter = adapter

    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images").execute()
            val puppies = call.body()

            runOnUiThread{
                if (puppies?.status == "success") {
                    initCharacter(puppies)
                } else {

                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun showError(){
        Toast.makeText(this, "Fallo algun proceso", Toast.LENGTH_SHORT).show()
    }



    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard(){
        val img = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        img.hideSoftInputFromWindow(binding.constraintRoot.windowToken, 0)
    }
}

