package org.wcci.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/artists")
public class ArtistController {
	@Autowired
	private ArtistRepository artistRepo;
	@Autowired
	private AlbumRepository albumRepo;

	@GetMapping("")
	public Iterable<Artist> fetchAllArtists(){
		Artist testArtist = new Artist("Ben");
		
		Album testAlbum1 = new Album("Greatest Hits", testArtist);
		
		testArtist = artistRepo.save(testArtist);
		
		testAlbum1 = albumRepo.save(testAlbum1);
		
		return artistRepo.findAll();
	}
	
}
