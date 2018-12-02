package com.example.tohid.test.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tohid.test.model.Owner;
import com.example.tohid.test.model.Repo;

@Database(entities = {Repo.class,Owner.class}, version = 1, exportSchema = false)
public abstract class RepoDB extends RoomDatabase {

    private static volatile RepoDB INSTANCE;
    public abstract RepoDao repoDao();

    static public RepoDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RepoDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RepoDB.class, "repodb")
                            .build();
                    // Create database here
                }
            }
        }
        return INSTANCE;
    }

}
