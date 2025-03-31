package learn.safari.controllers;

import learn.safari.data.AppUserRepository;
import learn.safari.models.AppUser;
import learn.safari.security.JwtConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AppUserRepository repository;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    Authentication authentication;

    @Autowired
    JwtConverter jwtConverter;

    String token;

    @BeforeEach
    void setup() {
        AppUser appUser = new AppUser(1, "admin@admin.com", "P@ssw0rd!", true, List.of("ADMIN"));
        token = jwtConverter.getTokenFromUser(appUser);
    }

    @Test
    void shouldAuthenticateKnownUserAndReturn200() throws Exception {
        AppUser appUser = new AppUser(1, "admin@admin.com", "P@ssw0rd!", true, List.of("ADMIN"));

        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin@admin.com");
        credentials.put("password", "P@ssw0rd!");

        when(repository.findByUsername("admin@admin.com")).thenReturn(appUser);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(appUser);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        String jsonIn = TestHelpers.serializeObjectToJson(credentials);

        var request = post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldNotAuthenticateUnknownUserAndReturn403() throws Exception {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin@admin.com");
        credentials.put("password", "P@ssw0rd!");

        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        String jsonIn = TestHelpers.serializeObjectToJson(credentials);

        var request = post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldCreateValidUserAndReturn201() throws Exception {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "user@user.com");
        credentials.put("password", "P@ssw0rd!");

        String jsonIn = TestHelpers.serializeObjectToJson(credentials);

        AppUser appUser = new AppUser(5, "user@user.com", "P@ssw0rd!", true, List.of("USER"));

        HashMap<String, Integer> createResult = new HashMap<>();
        createResult.put("appUserId", 5);

        String expectedJson = TestHelpers.serializeObjectToJson(createResult);

        when(repository.create(any())).thenReturn(appUser);

        var request = post("/create_account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotCreateInvalidUserAndReturn400() throws Exception {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "user@user.com");
        credentials.put("password", "bad-password");

        List<String> errorMessages = List.of("password must be at least 8 character and contain a digit, a letter, and a non-digit/non-letter");

        String jsonIn = TestHelpers.serializeObjectToJson(credentials);
        String expectedJson = TestHelpers.serializeObjectToJson(errorMessages);

        var request = post("/create_account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldRefreshTokenForValidTokenAndReturn200() throws Exception {
        var request = post("/refresh_token")
                .header("Authorization", "Bearer " + token);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldNotRefreshTokenForInvalidTokenAndReturn403() throws Exception {
        var request = post("/refresh_token")
                .header("Authorization", "Bearer fakebadtoken");

        mvc.perform(request).andExpect(status().isForbidden());
    }

    @Test
    void shouldNotRefreshTokenForMissingTokenAndReturn403() throws Exception {
        var request = post("/refresh_token");

        mvc.perform(request).andExpect(status().isForbidden());
    }
}