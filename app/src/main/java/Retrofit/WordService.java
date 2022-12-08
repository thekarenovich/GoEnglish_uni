package Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordService {
    @GET("entries/en/{word}")
    Call<List<Root>> listHotel(@Path("word") String word);
}
