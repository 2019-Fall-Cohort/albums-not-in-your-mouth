package org.wcci.albums.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wcci.albums.models.Album;
import org.wcci.albums.models.Artist;
import org.wcci.albums.models.Comment;
import org.wcci.albums.models.Tag;
import org.wcci.albums.repository.AlbumRepository;
import org.wcci.albums.repository.ArtistRepository;
import org.wcci.albums.repository.TagRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private ArtistRepository artistRepo;
    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private TestEntityManager entityManager;
    private Artist testArtist;
    private Album testAlbum1;

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Before
    public void setup(){
        testArtist = new Artist("Ben");
        testAlbum1 = new Album("Greatest Hits", testArtist);
    }

    @Test
    public void artistWillHaveAlbums() throws Exception {
        testArtist = artistRepo.save(testArtist);
        testAlbum1 = albumRepo.save(testAlbum1);

        flushAndClear();

        Artist retrievedArtist = artistRepo.findById(testArtist.getId()).get();

        assertEquals(testArtist, retrievedArtist);
        assertThat(retrievedArtist.getAlbums(), contains(testAlbum1));
    }

    @Test
    public void artistCanHaveComments() {
        Comment testComment = new Comment("This is an amazing comment!", "BOB");
        testArtist.addComment(testComment);
        testArtist = artistRepo.save(testArtist);

        flushAndClear();

        Artist retrievedArtist = artistRepo.findById(testArtist.getId()).get();
        assertThat(retrievedArtist.getComments(), contains(testComment));
    }
    @Test
    public void artistAndAlbumCanHaveTags(){
        testArtist = artistRepo.save(testArtist);
        testAlbum1 = albumRepo.save(testAlbum1);

        Tag testTag = tagRepo.save(new Tag("Testy Tagging"));

        testTag.addArtist(testArtist);
        testTag.addAlbum(testAlbum1);

        testTag = tagRepo.save(testTag);

        flushAndClear();

        Tag retrievedTag = tagRepo.findById(testTag.getId()).get();
        Album retrievedAlbum = albumRepo.findById(testAlbum1.getId()).get();
        Artist retrievedArtist = artistRepo.findById(testArtist.getId()).get();

        assertThat(retrievedTag.getAlbums(), contains(testAlbum1));
        assertThat(retrievedTag.getArtists(), contains(testArtist));
        assertThat(retrievedAlbum.getTags(), contains(testTag));
        assertThat(retrievedArtist.getTags(), contains(testTag));
    }
}
