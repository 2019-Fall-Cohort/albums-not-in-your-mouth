package org.wcci.albums.integration;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.wcci.albums.models.Artist;

import static org.hamcrest.CoreMatchers.is;
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    int port;
    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers;

    private String createArtistUrl;
    private String updateArtistUrl;
    private String addCommentToArtistUrl;
    private String addTagToArtistUrl;
    private Artist testArtist;
    private JSONObject jsonTestArtist;

    private void updateAddCommentToArtistUrl(Long artistId) {

    }

    @BeforeClass
    public void runBeforeAllTestMethods() throws JSONException {
        createArtistUrl = "http://localhost:" + port + "/api/artists";
        updateArtistUrl = "http://localhost:" + port + "/api/artists";

        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        jsonTestArtist = new JSONObject();
        jsonTestArtist.put("name", "Testy McTesterson");
    }

    @Ignore
    @Test
    public void loadingTestContext() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/artists", String.class);
        HttpStatus status = response.getStatusCode();
        org.hamcrest.MatcherAssert.assertThat(status, is(HttpStatus.OK));
    }

}
