package com.example.tohid.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tohid.test.api.GitHubService;
import com.example.tohid.test.model.Repo;
import com.example.tohid.test.model.RepoAdapter;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Repo>> {

    Button bChange;
    EditText idUser;
    GitHubService kaftar;
    RecyclerView recyclerView;


    private RepoAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bChange = findViewById(R.id.bchange);
        idUser = findViewById(R.id.id_user);
        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);



        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        kaftar = retrofit.create(GitHubService.class);

        bChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaftar.listRepos(idUser.getText().toString()).enqueue(MainActivity.this);
            }
        });
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        if (response.isSuccessful()) {
            List<Repo> repos = response.body();

            mAdapter = new RepoAdapter(repos);
            recyclerView.setAdapter(mAdapter);
        } else {
            System.out.println(response.errorBody());
        }
    }


    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {

    }
}
