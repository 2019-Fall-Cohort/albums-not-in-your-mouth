package org.wcci.albums.repository;

import org.springframework.data.repository.CrudRepository;
import org.wcci.albums.models.Tag;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag,Long> {
    Optional<Tag> findByName(String tagName);
}
