package org.wcci.albums.repository;

import org.springframework.data.repository.CrudRepository;
import org.wcci.albums.models.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
