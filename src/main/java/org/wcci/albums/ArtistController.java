package org.wcci.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
