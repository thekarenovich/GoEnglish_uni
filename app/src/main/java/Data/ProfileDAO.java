package Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDAO {

    @Insert
    public long insertProfile(ProfileDB profileDB);

    @Update
    public void updateProfile(ProfileDB profileDB);

    @Delete
    public void deleteProfile(ProfileDB profileDB);

    @Query("select * from profile where profile_id ==:profile_id ")
    public LiveData<List<ProfileDB>> getProfile(long profile_id);

    @Query("select * from profile where profile_id ==:profile_id ")
    public LiveData<ProfileDB> getImage(long profile_id);

    @Query("select * from profile")
    public LiveData<List<ProfileDB>> getAllProfileLive();
}