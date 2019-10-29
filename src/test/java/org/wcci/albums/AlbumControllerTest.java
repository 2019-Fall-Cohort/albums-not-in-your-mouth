package org.wcci.albums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class AlbumControllerTest {
	@InjectMocks
	private AlbumController underTest;

	@Mock
	private AlbumService albumService;

	private MockMvc mockMvc;
	
	private Album testAlbum;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
		testAlbum = new Album("testAlbum", new Artist("Jane"));
	}

	@Test
	public void fetchAllReturnsListOfAlbums() throws Exception {
		when(albumService.fetchAllAlbums()).thenReturn(Collections.singletonList(testAlbum));
		List<Album> retrievedAlbums = underTest.fetchAll();
		assertThat(retrievedAlbums, contains(testAlbum));
	}

	@Test
	public void fetchAllIsMappedCorrectlyAndReturnsAJsonList() throws Exception {
		when(albumService.fetchAllAlbums()).thenReturn(Collections.singletonList(testAlbum));
		mockMvc.perform(get("/api/albums"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].title", is(equalTo("testAlbum"))));
	}
	@Test
	public void fetchByIdReturnsSingleAlbum() {
		when(albumService.fetchAlbum(1L)).thenReturn(testAlbum);
		Album retrievedAlbum = underTest.fetchById(1L);
		assertThat(retrievedAlbum, is(testAlbum));
	}
	
	@Test
	public void fetchByIdIsMappedCorrectlyAndReturnsAJsonAlbum() throws Exception {
		when(albumService.fetchAlbum(1L)).thenReturn(testAlbum);
		mockMvc.perform(get("/api/albums/1"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$.title", is(equalTo("testAlbum"))));
	}
	
}
