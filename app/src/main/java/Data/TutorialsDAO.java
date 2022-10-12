package Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TutorialsDAO {

    @Insert
    public long insertTutorials(Tutorials tutorials);

    @Update
    public void updateTutorials(Tutorials tutorials);

    @Delete
    public void deleteTutorials(Tutorials tutorials);

    @Query("select * from tutorials")
    public List<Tutorials> getAllTutorials();

    @Query("select * from tutorials where tutorial_id ==:tutorial_id ")
    public Tutorials getTutorials(long tutorial_id);

    @Query("select * from tutorials")
    public LiveData<List<Tutorials>> getAllTutorialsLive();
}