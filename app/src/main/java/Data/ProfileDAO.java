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
    public void insertProfile(DBProfile dbProfile);

    @Update
    public void updateProfile(DBProfile dbProfile);

    @Delete
    public void deleteProfile(DBProfile dbProfile);

    @Query("select * from profile")
    public LiveData<List<DBProfile>> getAllProfileLive();

    @Query("select * from profile where id ==:id ")
    public LiveData<DBProfile> getProfile(long id);

    @Query("select * from profile where username ==:username ")
    public DBProfile getUsername(String username);

    @Query("select * from profile where password ==:password ")
    public LiveData<DBProfile> getPassword(String password);
}
