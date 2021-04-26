package com.example.tema2.interfaces;

import com.example.tema2.models.Album;
import com.example.tema2.models.User;

public interface FragmentCommunication {
    void addAlbumsFragment(User user);
    void addPhotosFragment(Album album);
}
