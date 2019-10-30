package org.wcci.albums.models;

import javax.persistence.Embeddable;

@Embeddable
public class Comment {
    private String content;
    private String author;

    protected Comment(){}
    public Comment(String content, String author) {

        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getContent() != null ? !getContent().equals(comment.getContent()) : comment.getContent() != null)
            return false;
        return getAuthor() != null ? getAuthor().equals(comment.getAuthor()) : comment.getAuthor() == null;
    }

    @Override
    public int hashCode() {
        int result = getContent() != null ? getContent().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }
}
