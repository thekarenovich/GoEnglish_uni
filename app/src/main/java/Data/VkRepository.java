package Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vk.VkMainResponse;
import vk.VkService;

public class VkRepository {
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.vk.com/method/")
            .build();
    private VkService vkService = retrofit.create(VkService.class);

    public LiveData<DBProfile> getInfoProfile(String token, String userId){

        MutableLiveData<DBProfile> result = new MutableLiveData<>();
        DBProfile profile = new DBProfile();

        vkService.getDataUser(token, userId).enqueue(new Callback<VkMainResponse>() {
            @Override
            public void onResponse(Call<VkMainResponse> call, Response<VkMainResponse> response) {
                if (response.isSuccessful()){

                    profile.setName(response.body().response.first_name + response.body().response.last_name);
                    profile.setUsername(response.body().response.screen_name);
                    result.setValue(profile);
                }
            }

            @Override
            public void onFailure(Call<VkMainResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;
    }
}
