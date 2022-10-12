package Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TutorialsRepository {
    private TutorialsDAO TutorialsDAO;
    private ProfileDAO profileDAO;
    private LiveData<List<Tutorials>> allTutorials;
    private LiveData<ProfileDB> image;

    public TutorialsRepository(Application application){
        TutorialsDB db = TutorialsDB.getInstance(application);
        TutorialsDAO = db.getTutorialsDAO();
        profileDAO = db.getProfileDAO();
        allTutorials = TutorialsDAO.getAllTutorialsLive();
        image = profileDAO.getImage(0);
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

    public void insertProfile(ProfileDB profileDB){
        //new InsertTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
        new InsertProfileAsyncTask(profileDAO).execute(profileDB);
    }

    public void updateProfile(ProfileDB profileDB){
        //new UpdateTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
        new UpdateProfileAsyncTask(profileDAO).execute(profileDB);
    }

    public void deleteProfile(ProfileDB profileDB){
        //new DeleteTutorialsAsyncTask(TutorialsDAO).execute(tutorials);
        new DeleteProfileAsyncTask(profileDAO).execute(profileDB);
    }


    public LiveData<List<Tutorials>> getAllTutorials(){
        return allTutorials;
    }

    public LiveData<ProfileDB> getImage(){
        return image;
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


    private static class InsertProfileAsyncTask extends AsyncTask<ProfileDB, Void, Void>{
        private ProfileDAO profileDAO;
        private InsertProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(ProfileDB... profileDBS) {
            profileDAO.insertProfile(profileDBS[0]);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<ProfileDB, Void, Void>{
        private ProfileDAO profileDAO;
        private UpdateProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(ProfileDB... profileDBS) {
            profileDAO.updateProfile(profileDBS[0]);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<ProfileDB, Void, Void>{
        private ProfileDAO profileDAO;
        private DeleteProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(ProfileDB... profileDBS) {
            profileDAO.deleteProfile(profileDBS[0]);
            return null;
        }
    }
}