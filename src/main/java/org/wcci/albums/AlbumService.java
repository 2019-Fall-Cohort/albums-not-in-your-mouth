package org.wcci.albums;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumService {
    @Autowired
    AlbumRepository albumRepo;
    public Album addAlbum(Album album) {
        return albumRepo.save(album);
    }

    public List<Album> fetchAllAlbums() {
        return (List<Album>) albumRepo.findAll();
    }

    public Album fetchAlbum(Long id) {
        return albumRepo.findById(id).get();
    }
}
