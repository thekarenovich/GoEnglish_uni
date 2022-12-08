package Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileRepository {

    private ProfileDAO profileDAO;
    private LiveData<List<DBProfile>> allProfile;
    private LiveData<DBProfile> profileId;

    public ProfileRepository(Application application){
        TutorialsDB db = TutorialsDB.getInstance(application);
        profileDAO = db.getProfileDao();
        allProfile = profileDAO.getAllProfileLive();

    }


    public void insertProfile(DBProfile profile){
        new ProfileRepository.InsertProfileAsyncTask(profileDAO).execute(profile);
    }

    public void updateProfile(DBProfile profile){
        new ProfileRepository.UpdateProfileAsyncTask(profileDAO).execute(profile);
    }

    public void deleteProfile(DBProfile profile){
        new ProfileRepository.DeleteProfileAsyncTask(profileDAO).execute(profile);
    }

    public LiveData<List<DBProfile>> getAllProfiles(){
        return allProfile;
    }

    public LiveData<DBProfile> getProfile(long id){
        return profileDAO.getProfile(id);
    }

    public DBProfile getUsername(String user){
        return profileDAO.getUsername(user);
    }

    private static class InsertProfileAsyncTask extends AsyncTask<DBProfile, Void, Void> {
        private ProfileDAO profileDAO;
        private InsertProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(DBProfile... dbProfiles) {
            profileDAO.insertProfile(dbProfiles[0]);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<DBProfile, Void, Void>{
        private ProfileDAO profileDAO;
        private UpdateProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(DBProfile... dbProfiles) {
            profileDAO.updateProfile(dbProfiles[0]);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<DBProfile, Void, Void>{
        private ProfileDAO profileDAO;
        private DeleteProfileAsyncTask(ProfileDAO profileDAO){
            this.profileDAO = profileDAO;
        }

        @Override
        protected Void doInBackground(DBProfile... dbProfiles) {
            profileDAO.deleteProfile(dbProfiles[0]);
            return null;
        }
    }
}
