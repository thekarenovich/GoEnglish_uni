package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Data.DBNote;
import Data.DBProfile;
import Data.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {
    private final ProfileRepository profileRepository;
    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
    }

    public void insertProfile(DBProfile profile){
        profileRepository.insertProfile(profile);
    }

    public void updateProfile(DBProfile profile){
        profileRepository.updateProfile(profile);
    }

    public void deleteProfile(DBProfile profile){
        profileRepository.deleteProfile(profile);
    }

    public LiveData<List<DBProfile>> getAllProfile(){
        return profileRepository.getAllProfiles();
    }

    public LiveData<DBProfile> getProfile(long id) {
        return profileRepository.getProfile(id);
    }

    public DBProfile getUsername(String username) {
        return profileRepository.getUsername(username);
    }
}
