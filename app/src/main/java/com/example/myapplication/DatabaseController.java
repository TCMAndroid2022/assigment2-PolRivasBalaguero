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
    private  PartidaDAO partidaDAO;
    private LiveData<List<Partida>>allPartidas;


    public DatabaseController(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        Userdao = db.Userdao();
        allUsers = Userdao.getAll();
        partidaDAO=db.Partidadao();
        allPartidas=partidaDAO.getAll();

    }

    public LiveData<List<User>> fetchAll() {
        return allUsers;
    }
    public LiveData<List<Partida>> getAllPartidabyusername(String username){
        return partidaDAO.getPartidasUsername(username);
    }
    public LiveData<List<Partida>> fetch() {
        return allPartidas;
    }


    public void setUser(String name) {

        User current = new User();

        current.name = name;
        boolean repetit=false;
        for(int x=0; x<allUsers.getValue().size();x++){
            if(allUsers.getValue().get(x).name.toUpperCase().equals(name.toUpperCase())) repetit=true;
        }
        if (!repetit && !name.equals("")) new insertAsyncTask(Userdao).execute(current);
    }

    public void setPartida(String username,String points){
        Partida partida= new Partida(username,points);
        new insertAsynTask(partidaDAO).execute(partida);

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

    private static class insertAsynTask {
        private PartidaDAO asyncDao;
        private Executor executor = Executors.newSingleThreadExecutor();

        insertAsynTask(PartidaDAO dao) {
            asyncDao = dao;
        }

        public void execute(Partida partida) {
            this.doInBackground(partida);
        }

        private void doInBackground(final Partida partida) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncDao.insertPartida(partida);
                }
            });
        }
    }

}
