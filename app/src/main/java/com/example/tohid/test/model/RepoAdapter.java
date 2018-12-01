package com.example.tohid.test.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tohid.test.R;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder> {



    private List<Repo> repoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.reponame);
            imageView = view.findViewById(R.id.repoimage);
        }
    }


    public RepoAdapter(List<Repo> repos) {
        this.repoList = repos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.name.setText(repo.getName());
        Glide.with(holder.imageView).load(repo.getOwner().getAvatarUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}