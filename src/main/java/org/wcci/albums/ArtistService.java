package org.wcci.albums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepo;

    public Artist saveArtist(Artist artist) {
        return artistRepo.save(artist);
    }

    public List<Artist> fetchAllArtists() {
        return (List<Artist>) artistRepo.findAll();
    }

    public Artist fetchArtist(Long id) {
        return artistRepo.findById(id).get();
    }
}
