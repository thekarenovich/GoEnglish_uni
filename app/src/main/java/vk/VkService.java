package vk;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkService {

    @GET("account.getProfileInfo?v=5.131")
    Call<VkMainResponse> getDataUser(
            @Query("access_token") String token,
            @Query("user_ids") String userIds);
}
