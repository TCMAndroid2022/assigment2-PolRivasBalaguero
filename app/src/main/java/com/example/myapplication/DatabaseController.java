package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseController {
    private UserDao Userdao;
    private LiveData<List<User>> allUsers;

    public DatabaseController(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        Userdao = db.Userdao();
        allUsers = Userdao.getAll();
    }

    public LiveData<List<User>> fetchAll() {
        return allUsers;
    }

    public void setUser(String name) {
        User current = new User();
        current.name = name;
        new insertAsyncTask(Userdao).execute(current);
    }

    /*private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncDao;

        insertAsyncTask(UserDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(User... Users) {
            asyncDao.insertUser(Users[0]);
            return null;
        }
    }*/

    private static class insertAsyncTask {
        private UserDao asyncDao;
        private Executor executor = Executors.newSingleThreadExecutor();

        insertAsyncTask(UserDao dao) {
            asyncDao = dao;
        }

        public void execute(User User) {
            this.doInBackground(User);
        }

        private void doInBackground(final User User) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncDao.insertUser(User);
                }
            });
        }
    }
}
