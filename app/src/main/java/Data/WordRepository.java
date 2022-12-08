package Data;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import Retrofit.Root;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import Retrofit.WordService;

public class WordRepository {
    MutableLiveData<List<Root>> list = new MutableLiveData<>();
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .build();
    private WordService ws = retrofit.create(WordService.class);

    public LiveData<String> getText(String word){

        MutableLiveData<String> result = new MutableLiveData<>();

        ws.listHotel(word).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        result.setValue(response.body().get(0).phonetics.get(1).text);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return result;
    }
}
