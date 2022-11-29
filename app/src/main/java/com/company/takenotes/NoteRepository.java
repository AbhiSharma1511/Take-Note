package com.company.takenotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){

        NoteDataBase database = NoteDataBase.getInstance(application);

        noteDao = database.noteDao();
        notes = noteDao.getAllNotes();
    }


// In present Executor service class is used instead of async class..
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void insert(Note note){
//        new InsertNoteAsyncTask(noteDao).execute(note);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });

    }

    public void update(Note note){
//        new updateNoteAsyncTask(noteDao).execute(note);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });

    }

    public void delete(Note note){
//        new DeleteNoteAsyncTask(noteDao).execute(note);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });

    }


    public LiveData<List<Note>> getAllNotes(){

        return  notes;

    }
















// Async class is used before the api level 30 and now this is depreciated....

    // 1. parameter for doInBackground method
    // 2. parameter for onProgressUpdate method
    // 3. parameter return type of doInBackground
/*
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void > {

        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);
            return null;
        }
    }

        private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void > {

        private NoteDao noteDao;

        public updateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

        private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void > {

        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }
*/



}
