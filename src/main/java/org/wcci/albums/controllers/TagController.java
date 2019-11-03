package org.wcci.albums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.albums.models.Album;
import org.wcci.albums.models.Artist;
import org.wcci.albums.models.Tag;
import org.wcci.albums.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    TagRepository tagRepo;


    @GetMapping("")
    public Iterable<Tag> fetchAll(){
        return tagRepo.findAll();
    }
    @GetMapping("/{id}")
    public Tag fetchIndividualTag(@PathVariable Long id){
        return tagRepo.findById(id).get();
    }
    @GetMapping("/{id}/albums")
    public Iterable<Album> fetchTagAlbums(@PathVariable Long id){
        return tagRepo.findById(id).get().getAlbums();
    }
    @GetMapping("{id}/artists")
    public Iterable<Artist> fetchTagArtists(@PathVariable Long id){
        return tagRepo.findById(id).get().getArtists();
    }
}
