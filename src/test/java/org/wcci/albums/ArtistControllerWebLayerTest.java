package org.wcci.albums;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtistController.class)
@RunWith(SpringRunner.class)
public class ArtistControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistService artistService;

    private Artist testArtist;
    @Before
    public void setup(){
        testArtist = new Artist("Jane");
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
}
