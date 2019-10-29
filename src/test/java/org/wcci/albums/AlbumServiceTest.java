package org.wcci.albums;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumServiceTest {

    @InjectMocks
    private AlbumService underTest;

    @Mock
    private AlbumRepository albumRepo;
    @Mock
    private Album mockAlbum;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddAlbumToRepo() {
        Album storedAlbum = underTest.addAlbum(mockAlbum);
        verify(albumRepo).save(mockAlbum);
    }

    @Test
    public void shouldFetchAllAlbums() {
        when(albumRepo.findAll()).thenReturn(Collections.singletonList(mockAlbum));
        List<Album> retrievedAlbums = underTest.fetchAllAlbums();
        verify(albumRepo).findAll();
        assertThat(retrievedAlbums, contains(mockAlbum));
    }

    @Test
    public void shouldFetchSingleAlbum(){
        when(albumRepo.findById(1L)).thenReturn(Optional.of(mockAlbum));
        Album retrievedAlbum = underTest.fetchAlbum(1L);
        verify(albumRepo).findById(1L);
        assertThat(retrievedAlbum, is(equalTo(mockAlbum)));
    }
}
