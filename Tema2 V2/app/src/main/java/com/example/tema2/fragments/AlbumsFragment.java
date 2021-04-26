package com.example.tema2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tema2.R;
import com.example.tema2.VolleySingleton;
import com.example.tema2.adapters.AlbumsAdapter;
import com.example.tema2.interfaces.OnClickListener;
import com.example.tema2.interfaces.FragmentCommunication;
import com.example.tema2.models.Album;
import com.example.tema2.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AlbumsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private User user;
    private ArrayList<Album> albums = new ArrayList<Album>();

    AlbumsAdapter albumsAdapter = new AlbumsAdapter(albums, new OnClickListener() {
        @Override
        public void onUserClick(User user) {

        }

        @Override
        public void onAlbumCLick(Album album) {
            if (activityFragmentCommunication != null) {
                activityFragmentCommunication.addPhotosFragment(album);
            }
        }

        @Override
        public void onArrowClick(User user) {

        }
    });

    FragmentCommunication activityFragmentCommunication;

    public AlbumsFragment(User user) {
        this.user = user;
    }

    public static AlbumsFragment newInstance(String param1, String param2, User newUser) {
        AlbumsFragment fragment = new AlbumsFragment(newUser);
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
        fetchAlbums();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        ProgressBar simpleProgressBar = (ProgressBar) view.findViewById(R.id.pBar);
        simpleProgressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.albumsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(albumsAdapter);

        simpleProgressBar.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            activityFragmentCommunication = (FragmentCommunication) context;
        }
    }

    public void fetchAlbums() {
        VolleySingleton volleyConfigSingleton = VolleySingleton.getInstance(getContext());
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/users/";
        url += user.getId() + "/albums";

        StringRequest getAlbumsRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleAlbumsResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(getAlbumsRequest);
    }

    public void handleAlbumsResponse(String response){
        try {
            user.getAlbums().clear();

            JSONArray albumsJsonArray = new JSONArray(response);

            for (int index = 0; index < albumsJsonArray.length(); index++) {

                JSONObject albumObject = albumsJsonArray.getJSONObject(index);

                if (albumObject != null) {
                    long userId = albumObject.getLong("userId");
                    long id = albumObject.getLong("id");
                    String title = albumObject.getString("title");

                    Album album = new Album(id, userId, title);

                    if (!user.getAlbums().contains(album)) {
                        user.getAlbums().add(album);
                        albums.add(album);
                    }
                }
            }
            albumsAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}