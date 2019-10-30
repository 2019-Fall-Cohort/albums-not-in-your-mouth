package org.wcci.albums.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.albums.models.Album;
import org.wcci.albums.services.AlbumService;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
	
	private AlbumService albumService;
	
	@GetMapping("")
	public List<Album> fetchAll() {
		return albumService.fetchAllAlbums();
	}
	
	@GetMapping("/{id}")
	public Album fetchById(@PathVariable Long id) {
		return albumService.fetchAlbum(id);
	}

}
