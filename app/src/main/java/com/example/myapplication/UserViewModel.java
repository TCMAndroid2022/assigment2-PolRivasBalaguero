package com.example.myapplication;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
        private DatabaseController repository;
        private LiveData<List<User>> allUsers;

        public UserViewModel(@NonNull Application application) {
            super(application);
            repository = new DatabaseController(application);
            allUsers = repository.fetchAll();
        }

        LiveData<List<User>> getAllUsers() {
            return allUsers;
        }

        void insert(String task) {
            repository.setUser(task);
        }
    }


