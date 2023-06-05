import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendApiClient {
    private const val BASE_URL = "http://localhost:5000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val backendService: BackendService = retrofit.create(BackendService::class.java)
}
