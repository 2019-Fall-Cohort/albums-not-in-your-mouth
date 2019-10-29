package org.wcci.albums;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ArtistControllerTest {

    @InjectMocks
    ArtistController underTest;

    @Mock
    ArtistService artistService;
    private MockMvc mockMvc;
    private Artist testArtist;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        testArtist = new Artist("Jane");
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void fetchAllReturnsListOfArtists(){
        when(artistService.fetchAllArtists()).thenReturn(Collections.singletonList(testArtist));
        List<Artist> retrievedArtists = underTest.fetchAll();
        assertThat(retrievedArtists, contains(testArtist));
    }

    @Test
    public void shouldReturnAllArtists() throws Exception {
        when(artistService.fetchAllArtists()).thenReturn(Collections.singletonList(testArtist));
        mockMvc.perform(get("/api/artists"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].name", is(equalTo("Jane"))));
    }
    
    @Ignore
    @Test
    public void shouldAddArtist() throws Exception {
        mockMvc.perform(post(".api/artists/add").content(
                "{" +
                        "\"name\": \"Jane\"" +
                "}"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
