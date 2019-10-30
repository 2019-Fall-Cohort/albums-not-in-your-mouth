package org.wcci.albums.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wcci.albums.ArtistNotFoundException;
import org.wcci.albums.models.Artist;
import org.wcci.albums.repository.ArtistRepository;

public class ArtistServiceTest {
    @InjectMocks
    private ArtistService underTest;

    @Mock
    private ArtistRepository artistRepo;
    @Mock
    private Artist mockArtist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddAlbumToRepo() {
        Artist storedArtist = underTest.saveArtist(mockArtist);
        verify(artistRepo).save(mockArtist);
    }

    @Test
    public void shouldFetchAllAlbums() {
        when(artistRepo.findAll()).thenReturn(Collections.singletonList(mockArtist));
        List<Artist> retrievedArtists = underTest.fetchAllArtists();
        verify(artistRepo).findAll();
        assertThat(retrievedArtists, contains(mockArtist));
    }

    @Test
    public void shouldFetchSingleArtist(){
        when(artistRepo.findById(1L)).thenReturn(Optional.of(mockArtist));
        Artist retrievedArtist = underTest.fetchArtist(1L);
        verify(artistRepo).findById(1L);
        assertThat(retrievedArtist, is(equalTo(mockArtist)));
    }
    
    @Test
    public void shouldThrowNotFoundExceptionWhenArtistDoesntExist() {
    	 when(artistRepo.findById(1L)).thenReturn(Optional.empty());
    	 try {
    		 underTest.fetchArtist(1L);
    		 fail("Exception not thrown");
    	 }catch (ArtistNotFoundException e) {
    		 assertThat(e.getMessage(), is(equalTo("Artist not found.")));
    	 }
    }
}
