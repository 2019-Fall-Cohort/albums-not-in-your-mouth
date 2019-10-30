package org.wcci.albums.repository;

import org.springframework.data.repository.CrudRepository;
import org.wcci.albums.models.Tag;

public interface TagRepository extends CrudRepository<Tag,Long> {
}
