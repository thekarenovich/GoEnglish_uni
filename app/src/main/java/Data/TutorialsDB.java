package Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tutorials.class, ProfileDB.class}, version = 1)
public abstract class TutorialsDB extends RoomDatabase {

    public abstract TutorialsDAO getTutorialsDAO();
    public abstract ProfileDAO getProfileDAO();

    private static volatile TutorialsDB INSTANCE;
    public static TutorialsDB getInstance(Context context){
        if (INSTANCE == null){
            synchronized (TutorialsDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TutorialsDB.class,"TutorialsDB").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}