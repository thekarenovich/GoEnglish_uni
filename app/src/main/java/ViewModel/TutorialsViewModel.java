package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Data.TutorialsRepository;
import Model.Tutorials;

public class TutorialsViewModel extends AndroidViewModel {
    private TutorialsRepository tutorialsRepository;
    private LiveData<List<Tutorials>> allTutorials;

    public TutorialsViewModel(@NonNull Application application) {
        super(application);
        tutorialsRepository = new TutorialsRepository(application);
        allTutorials = tutorialsRepository.getAllTutorials();
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
}