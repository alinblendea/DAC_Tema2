package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.R;
import com.example.tema2.models.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {
    ArrayList<User> users;

    public MyAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_cell, parent, false);
        UserViewHolder viewHolder= new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView username;

        UserViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            username = view.findViewById(R.id.username);
        }

        void bind(User user) {
            name.setText(user.getName());
            username.setText(user.getUsername());
        }
    }
}
