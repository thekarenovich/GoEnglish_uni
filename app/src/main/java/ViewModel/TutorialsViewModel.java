package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Data.DBNote;
import Data.TutorialsRepository;
import Data.Tutorials;

public class TutorialsViewModel extends AndroidViewModel {
    private TutorialsRepository tutorialsRepository;
    private LiveData<List<Tutorials>> allTutorials;
    private LiveData<List<DBNote>> getAllNotes;

    public TutorialsViewModel(@NonNull Application application) {
        super(application);
        tutorialsRepository = new TutorialsRepository(application);
        allTutorials = tutorialsRepository.getAllTutorials();
        getAllNotes = tutorialsRepository.getAllNotes();

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


    public void insert(DBNote note){
        tutorialsRepository.insertNote(note);
    }

    public void update(DBNote note){
        tutorialsRepository.updateNote(note);
    }

    public void delete(DBNote note){
        tutorialsRepository.deleteNote(note);
    }

    public LiveData<List<DBNote>> getAllNotes(){
        return getAllNotes;
    }

}