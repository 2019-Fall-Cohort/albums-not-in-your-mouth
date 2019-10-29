package org.wcci.albums;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArtistService {
    @Autowired
    private ArtistRepository artistRepo;

    public Artist addArtist(Artist artist) {
        return artistRepo.save(artist);
    }

    public List<Artist> fetchAllArtists() {
        return (List<Artist>) artistRepo.findAll();
    }

    public Artist fetchArtist(Long id) {
        return artistRepo.findById(id).get();
    }
}
