package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.R;
import com.example.tema2.interfaces.OnUserItemClick;
import com.example.tema2.models.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {
    ArrayList<User> users;
    OnUserItemClick onUserItemClick;

    public MyAdapter(ArrayList<User> users, OnUserItemClick onUserItemClick) {
        this.users = users;
        this.onUserItemClick = onUserItemClick;
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
        boolean showPosts = users.get(position).isShowPosts();
        holder.postsConstraintLayout.setVisibility(showPosts ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView username;
        private TextView posts;
        private ImageView arrow;
        private ConstraintLayout postsConstraintLayout;
        private View view;

        UserViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            username = view.findViewById(R.id.username);
            posts = view.findViewById(R.id.posts_textView);
            postsConstraintLayout = view.findViewById(R.id.posts);
            arrow = view.findViewById(R.id.arrow);
        }

        void bind(User user) {
            name.setText(user.getName());
            username.setText(user.getUsername());

            String userPosts = "User's posts:\n";
            for(int index=0; index< user.getPosts().size();index++) {
                userPosts+=(index+1)+") "+user.getPosts().get(index).getTitle()+"\n";
            }
            posts.setText(userPosts);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onUserItemClick != null) {
                        onUserItemClick.onUserClick(user);
                    }
                }
            });

            arrow.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    user.setShowPosts(!user.isShowPosts());
                    onUserItemClick.onArrowClick(user);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
