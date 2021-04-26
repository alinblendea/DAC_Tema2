package com.example.tema2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tema2.R;
import com.example.tema2.VolleySingleton;
import com.example.tema2.adapters.PhotoAdapter;
import com.example.tema2.interfaces.FragmentCommunication;
import com.example.tema2.models.Album;
import com.example.tema2.models.Photo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class PhotosFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private Album album;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    FragmentCommunication activityFragmentCommunication;
    PhotoAdapter photoAdapter = new PhotoAdapter(photos);

    public PhotosFragment(Album album) {
        this.album = album;
    }

    public static PhotosFragment newInstance(String param1, String param2, Album album) {
        PhotosFragment fragment = new PhotosFragment(album);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fetchPhotos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.photos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(photoAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            activityFragmentCommunication = (FragmentCommunication) context;
        }
    }

    public void fetchPhotos() {
        VolleySingleton volleyConfigSingleton = VolleySingleton.getInstance(getContext());
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/albums/";
        url += album.getId() + "/photos";

        StringRequest getPhotosRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handlePhotosResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(getPhotosRequest);
    }

    public void handlePhotosResponse(String response){
        try {
            JSONArray photosJsonArray = new JSONArray(response);
            for (int index = 0; index < photosJsonArray.length(); index++) {
                JSONObject photoObject = photosJsonArray.getJSONObject(index);
                if (photoObject != null) {
                    long userId = photoObject.getLong("albumId");
                    long id = photoObject.getLong("id");
                    String title = photoObject.getString("title");
                    String url = photoObject.getString("url")+".png";
                    String thumbnailUri = photoObject.getString("thumbnailUrl");

                    Photo photo = new Photo(id, userId, title, url, thumbnailUri);

                    if (!album.getPhotos().contains(album)) {
                        album.getPhotos().add(photo);
                        photos.add(photo);
                    }
                }
            }
            photoAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}