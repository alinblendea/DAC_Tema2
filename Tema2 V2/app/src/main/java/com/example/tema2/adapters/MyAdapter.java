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
import com.example.tema2.interfaces.OnClickListener;
import com.example.tema2.models.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {
    private ArrayList<User> users;
    private OnClickListener onItemClickListener;

    public MyAdapter(ArrayList<User> users, OnClickListener onItemClickListener) {
        this.users = users;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cell, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
        boolean postsVisible = users.get(position).arePostsVisible();
        holder.postsConstraintLayout.setVisibility(postsVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView info;
        private ImageView arrow;
        private TextView posts;
        private ConstraintLayout postsConstraintLayout;
        private View view;

        public UserViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            info = view.findViewById(R.id.info);
            posts = view.findViewById(R.id.postsTextView);
            postsConstraintLayout = view.findViewById(R.id.posts);
            arrow = view.findViewById(R.id.arrowImg);
        }

        public void bind(User user) {
            name.setText(user.getName());
            info.setText("E-mail: " + user.getEmailAddress());
            String userPosts = "Posts:\n";

            for(int index=0; index< user.getPosts().size();index++) {
                userPosts+=(index+1)+". "+user.getPosts().get(index).getTitle()+"\n";
            }
            posts.setText(userPosts);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onUserClick(user);
                }
            });

            arrow.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    user.setPostsVisible(!user.arePostsVisible());
                    onItemClickListener.onArrowClick(user);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

