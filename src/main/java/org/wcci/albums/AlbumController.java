package org.wcci.albums;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
	private AlbumRepository albumRepo;
	
	@GetMapping("")
	public List<Album> fetchAll() {
		return (List<Album>) albumRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Album fetchById(@PathVariable Long id) {
		return albumRepo.findById(id).get();
	}

}
