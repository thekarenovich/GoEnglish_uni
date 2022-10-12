package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Data.ProfileDAO;
import Data.ProfileDB;
import Data.TutorialsRepository;
import Data.Tutorials;

public class TutorialsViewModel extends AndroidViewModel {
    private TutorialsRepository tutorialsRepository;
    private LiveData<List<Tutorials>> allTutorials;
    private LiveData<ProfileDB> img;

    public TutorialsViewModel(@NonNull Application application) {
        super(application);
        tutorialsRepository = new TutorialsRepository(application);
        allTutorials = tutorialsRepository.getAllTutorials();
        img = tutorialsRepository.getImage();
    }

    // Methods ViewModel for the local database
    public void insert(Tutorials tutorials){
        tutorialsRepository.insert(tutorials);
    }

    public void update(Tutorials tutorials){
        tutorialsRepository.update(tutorials);
    }

    public void delete(Tutorials tutorials){
        tutorialsRepository.delete(tutorials);
    }

    public LiveData<List<Tutorials>> getAllTutorials(){
        return allTutorials;
    }


    public void insertProfile(ProfileDB profileDB){
        tutorialsRepository.insertProfile(profileDB);
    }

    public void updateProfile(ProfileDB profileDB){
        tutorialsRepository.updateProfile(profileDB);
    }

    public void delete(ProfileDB profileDB){
        tutorialsRepository.deleteProfile(profileDB);
    }

    public LiveData<ProfileDB> getImage(){
        return img;
    }

}