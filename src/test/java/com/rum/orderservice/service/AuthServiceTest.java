package com.rum.orderservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    public void setup() {
        authService = new AuthService();
    }

    @Test
    @DisplayName("Should return username when user is authenticated")
    void shouldReturnUsernameWhenUserIsAuthenticated() {
        UserDetails userDetails = new User("user1", "password", new ArrayList<>());
        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = authService.getCurrentUser();

        assertEquals("user1", username);
    }

    @Test
    @DisplayName("Should return default username when user is not authenticated")
    void shouldReturnDefaultUsernameWhenUserIsNotAuthenticated() {
        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn("anonymousUser");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = authService.getCurrentUser();

        assertEquals("user1", username);
    }
}