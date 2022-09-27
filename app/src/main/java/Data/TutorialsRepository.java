package Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import Model.Tutorials;

public class TutorialsRepository {
    private TutorialsDAO TutorialsDAO;
    private LiveData<List<Tutorials>> allTutorials;

    public TutorialsRepository(Application application){
        TutorialsDB db = TutorialsDB.getInstance(application);
        TutorialsDAO = db.getTutorialsDAO();
        allTutorials = TutorialsDAO.getAllTutorialsLive();
    }

    // Methods for the local database
    public void insert(Tutorials tutorials){
        new InsertTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
    }

    public void update(Tutorials tutorials){
        new UpdateTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
    }

    public void delete(Tutorials tutorials){
        new DeleteTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
    }

    public LiveData<List<Tutorials>> getAllTutorials(){
        return allTutorials;
    }

    private static class InsertTutorialsAsyncTask extends AsyncTask<Tutorials, Void, Void>{
        private TutorialsDAO tutorialsDAO;
        private InsertTutorialsAsyncTask(TutorialsDAO tutorialsDAO){
            this.tutorialsDAO = tutorialsDAO;
        }

        @Override
        protected Void doInBackground(Tutorials... tutorials) {
            tutorialsDAO.insertTutorials(tutorials[0]);
            return null;
        }
    }

    private static class UpdateTutorialsAsyncTask extends AsyncTask<Tutorials, Void, Void>{
        private TutorialsDAO tutorialsDAO;
        private UpdateTutorialsAsyncTask(TutorialsDAO tutorialsDAO){
            this.tutorialsDAO = tutorialsDAO;
        }

        @Override
        protected Void doInBackground(Tutorials... tutorials) {
            tutorialsDAO.updateTutorials(tutorials[0]);
            return null;
        }
    }

    private static class DeleteTutorialsAsyncTask extends AsyncTask<Tutorials, Void, Void>{
        private TutorialsDAO tutorialsDAO;
        private DeleteTutorialsAsyncTask(TutorialsDAO tutorialsDAO){
            this.tutorialsDAO = tutorialsDAO;
        }

        @Override
        protected Void doInBackground(Tutorials... tutorials) {
            tutorialsDAO.deleteTutorials(tutorials[0]);
            return null;
        }
    }
}