package com.uniamerica.reminder.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.reminder.dto.UserDTO;
import com.uniamerica.reminder.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDTOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testFindAll() throws Exception {
        List<UserDTO> userDTOS = Collections.singletonList(new UserDTO(1L, "John"));
        when(userService.findAll()).thenReturn(userDTOS);

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk());
    }

    @Test
    public void testCadastrar() throws Exception {
        UserDTO userDTO = new UserDTO(null, "John");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/users/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isOk());
    }
}
