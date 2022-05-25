package com.meubixin.api.security;

import com.meubixin.api.controller.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should not allow access to unauthenticated user")
    void shouldNotAllowAccessToUnauthenticatedUser() throws Exception{

        // when - action
        ResultActions response = mockMvc.perform( post("/api/v1/user"));

        // then - verify
        response.andExpect(status().isUnauthorized());

    }

    @Test
    @DisplayName("Should authenticate with admin user")
    void shouldAuthenticateWithAdminUser() throws Exception{


        // when - action
        ResultActions response = mockMvc.perform(
                post("/api/v1/user")
                    .contextPath("/api/v1")
                    .servletPath("/user")
                    .with(httpBasic("admin@email.com","admin"))

        );

        // then - verify
        response
            .andExpect(status().isOk())
            .andExpect(header().exists("Authorization"))
                .andExpect(content().json("{\"id\":2 ,\"email\":\"admin@email.com\",\"enabled\":true,\"created_at\":\"2022-05-23T00:00:00\",\"updated_at\":\"2022-05-23T00:00:00\"}"));
    }

}
