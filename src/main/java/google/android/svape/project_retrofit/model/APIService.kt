package google.android.svape.project_retrofit.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getDogsByBreeds(@Url url: String): Call<DogsResponse>
}