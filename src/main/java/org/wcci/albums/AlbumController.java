package org.wcci.albums;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
