package org.wcci.albums.controllers;

import java.util.List;
import java.util.Optional;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wcci.albums.models.Album;
import org.wcci.albums.models.Tag;
import org.wcci.albums.repository.TagRepository;
import org.wcci.albums.services.AlbumService;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	@Autowired
	private TagRepository tagRepo;
	
	@GetMapping("")
	public List<Album> fetchAll() {
		return albumService.fetchAllAlbums();
	}
	
	@GetMapping("/{id}")
	public Album fetchById(@PathVariable Long id) {
		return albumService.fetchAlbum(id);
	}

	@PatchMapping("/{id}/add-tag/{tagName}")
	public Album addTag(@PathVariable long id, @PathVariable String tagName) {
		Album album = albumService.fetchAlbum(id);
		Tag tag;
		Optional<Tag> tagOptional = tagRepo.findByName(tagName);
		if(tagOptional.isPresent()){
			tag= tagOptional.get();
		}else{
			tag=new Tag(tagName);
		}

		tag.addAlbum(album);
		tagRepo.save(tag);


		return albumService.fetchAlbum(id);
	}
}
