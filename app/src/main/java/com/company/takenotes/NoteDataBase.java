package com.company.takenotes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {


    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){

        if (instance==null){

            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_database")
//                    .allowMainThreadQueries() // not recommended
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback =  new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();  // this is async class uses..

            NoteDao noteDao = instance.noteDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    noteDao.insert(new Note("Title1","Description1"));
                    noteDao.insert(new Note("Title2","Description2"));
                    noteDao.insert(new Note("Title3","Description3"));
                    noteDao.insert(new Note("Title4","Description4"));

                }
            });
        }
    };





// This is async task operation....
    /*
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDataBase dataBase) {
            noteDao = dataBase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Title1","Description1"));
            noteDao.insert(new Note("Title2","Description2"));
            noteDao.insert(new Note("Title3","Description3"));
            noteDao.insert(new Note("Title4","Description4"));
            return null;
        }
    }

     */

}
