package org.wcci.albums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wcci.albums.models.Artist;
import org.wcci.albums.models.Comment;
import org.wcci.albums.models.Tag;
import org.wcci.albums.repository.TagRepository;
import org.wcci.albums.services.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private TagRepository tagRepo;

    @GetMapping("")
    public List<Artist> fetchAll() {
        return artistService.fetchAllArtists();
    }

    @GetMapping("/{id}")
    public Artist fetchById(@PathVariable long id) {
        return artistService.fetchArtist(id);
    }
    
    @PostMapping("")
    public Artist addArtist(@RequestBody Artist artist) {
    	return artistService.saveArtist(artist);
    }

    @PatchMapping("/{id}/add-comment")
    public Artist addComment(@PathVariable long id, @RequestBody Comment comment) {
        Artist artist = artistService.fetchArtist(id);
        artist.addComment(comment);
        return artistService.saveArtist(artist);
    }

    @PatchMapping("/{id}/add-tag")
    public Artist addTag(@PathVariable long id, @RequestBody Tag tag) {
        Artist artist = artistService.fetchArtist(id);
        tag.addArtist(artist);
        tagRepo.save(tag);


        return artistService.fetchArtist(id);
    }
}
