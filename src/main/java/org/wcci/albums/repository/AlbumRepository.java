package org.wcci.albums.repository;

import org.springframework.data.repository.CrudRepository;
import org.wcci.albums.models.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
