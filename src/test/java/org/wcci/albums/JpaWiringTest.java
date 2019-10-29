package org.wcci.albums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private ArtistRepository artistRepo;
    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void artistWillHaveAlbums() throws Exception {
        Artist testArtist = new Artist("Ben");

        Album testAlbum1 = new Album("Greatest Hits", testArtist);

        testArtist = artistRepo.save(testArtist);

        testAlbum1 = albumRepo.save(testAlbum1);

        entityManager.flush();
        entityManager.clear();

        Artist retrievedArtist = artistRepo.findById(testArtist.getId()).get();

        assertEquals(testArtist, retrievedArtist);
        assertThat(retrievedArtist.getAlbums(), contains(testAlbum1));
    }

    @Test
    public void artistCanHaveComments() {
        Artist testArtist = new Artist("Ben");
        Comment testComment = new Comment("This is an amazing comment!", "BOB");
        testArtist.addComment(testComment);
        testArtist = artistRepo.save(testArtist);

        entityManager.flush();
        entityManager.clear();

        Artist retrievedArtist = artistRepo.findById(testArtist.getId()).get();
        assertThat(retrievedArtist.getComments(), contains(testComment));
    }
}
