package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.tema2.R;
import com.example.tema2.VolleySingleton;
import com.example.tema2.models.Photo;
import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ImageViewHolder>{
    private ArrayList<Photo> photos = new ArrayList<Photo>();

    public PhotoAdapter(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_item_cell, parent, false);
        PhotoAdapter.ImageViewHolder imageViewHolder = new PhotoAdapter.ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ImageViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView imageView;

        public ImageViewHolder(@NonNull View view) {
            super(view);
            this.view=view;
            imageView = view.findViewById(R.id.photoView);
        }

        public void bind(Photo photo) {
            ImageLoader imageLoader = VolleySingleton.getInstance(imageView.getContext()).getImageLoader();
            imageLoader.get(photo.getUrl(), new ImageLoader.ImageListener(){
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }
}
