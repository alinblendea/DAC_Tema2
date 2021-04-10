package com.example.tema2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tema2.R;
import com.example.tema2.adapters.MyAdapter;
import com.example.tema2.models.Address;
import com.example.tema2.models.Company;
import com.example.tema2.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<User> users = new ArrayList<User>();
        User user1 = new User(1, "Aleen", "alinbld", "alinblendea@yahoo.com", new Address(), "0787543049", "asds.com", new Company());
        User user2 = new User(2, "Aleen2", "alinbld2", "alinblendea@yahoo.com", new Address(), "0787543049", "asds.com", new Company());
        User user3 = new User(3, "Aleen3", "alinbld3", "alinblendea@yahoo.com", new Address(), "0787543049", "asds.com", new Company());
        User user4 = new User(4, "Aleen4", "alinbld4", "alinblendea@yahoo.com", new Address(), "0787543049", "asds.com", new Company());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        recyclerView.setAdapter(new MyAdapter(users));

        return view;
    }
}