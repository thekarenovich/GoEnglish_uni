package Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TutorialsRepository {
    private TutorialsDAO TutorialsDAO;
    private LiveData<List<Tutorials>> allTutorials;

    private NoteDAO noteDAO;
    private LiveData<List<DBNote>> allNote;

    public TutorialsRepository(Application application){
        TutorialsDB db = TutorialsDB.getInstance(application);
        TutorialsDAO = db.getTutorialsDAO();
        allTutorials = TutorialsDAO.getAllTutorialsLive();

        noteDAO = db.getNoteDao();
        allNote = noteDAO.getAllNoteLive();
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

    public void insertNote(DBNote note){
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }

    public void updateNote(DBNote note){
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }

    public void deleteNote(DBNote note){
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }

    public LiveData<List<DBNote>> getAllNotes(){
        return allNote;
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


    private static class InsertNoteAsyncTask extends AsyncTask<DBNote, Void, Void> {
        private NoteDAO noteDAO;
        private InsertNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(DBNote... notes) {
            noteDAO.insertNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<DBNote, Void, Void>{
        private NoteDAO noteDAO;
        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(DBNote... notes) {
            noteDAO.updateNote(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<DBNote, Void, Void>{
        private NoteDAO noteDAO;
        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(DBNote... notes) {
            noteDAO.deleteNote(notes[0]);
            return null;
        }
    }
}