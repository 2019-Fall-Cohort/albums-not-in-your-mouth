package org.wcci.albums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Artist {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy="artist")
	private List<Album> albums;
	@ElementCollection
	private List<Comment> comments;

	protected Artist() {}
	public Artist(String name) {
		this.name = name;
	
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void addComment(Comment comment) {
		if(comments==null){
			comments = new ArrayList<>();
		}
		comments.add(comment);
	}

	public List<Comment> getComments() {
		return comments;
	}
}
