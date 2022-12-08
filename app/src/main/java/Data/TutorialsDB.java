package Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tutorials.class, DBNote.class, DBProfile.class}, version = 1)
public abstract class TutorialsDB extends RoomDatabase {

    public abstract TutorialsDAO getTutorialsDAO();
    public abstract NoteDAO getNoteDao();
    public abstract ProfileDAO getProfileDao();

    private static volatile TutorialsDB INSTANCE;
    public static TutorialsDB getInstance(Context context){
        if (INSTANCE == null){
            synchronized (TutorialsDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TutorialsDB.class,"TutorialsDB").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}