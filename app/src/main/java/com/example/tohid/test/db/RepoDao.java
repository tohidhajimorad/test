package com.example.tohid.test.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tohid.test.model.Owner;
import com.example.tohid.test.model.Repo;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM Owner")
    Owner[] getAllOwners();

    @Insert
    void insert(Owner owner);

    @Query("SELECT * FROM Repo")
    Repo[] getAllRepos();


    @Insert
    void insert(Repo repo);

    @Insert
    void insert(List<Repo> repos);

}


