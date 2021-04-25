package com.example.tema2.interfaces;

import com.example.tema2.models.Album;
import com.example.tema2.models.User;

public interface OnUserItemClick {
    void onUserClick(User user);
    void onAlbumClick(Album album);
    void onArrowClick(User user);
}
