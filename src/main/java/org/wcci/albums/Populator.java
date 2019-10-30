package org.wcci.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.albums.models.Artist;
import org.wcci.albums.repository.ArtistRepository;
@Component
public class Populator implements CommandLineRunner {
	@Autowired
	private ArtistRepository artistRepo;

	@Override
	public void run(String... args) throws Exception {
		artistRepo.save(new Artist("Bob"));
		artistRepo.save(new Artist("Jane"));
	}

}
