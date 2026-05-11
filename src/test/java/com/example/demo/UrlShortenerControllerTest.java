package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
class UrlShortenerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        // Wir bauen den MockMvc manuell auf, das ist absolut sicher gegen Import-Fehler!
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testShortenEndpoint() throws Exception {
        mockMvc.perform(post("/shorten")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("https://www.google.com"))
                .andExpect(status().isOk());
    }

    @Test
    void testRedirectEndpointNotFound() throws Exception {
        mockMvc.perform(get("/falscheID"))
                .andExpect(status().isNotFound());
    }
}

