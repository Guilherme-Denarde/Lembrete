package com.uniamerica.reminder.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.reminder.dto.ReminderDTO;
import com.uniamerica.reminder.service.ReminderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReminderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllReminders() throws Exception {
        mockMvc.perform(get("/api/reminders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReminder() throws Exception {
        ReminderDTO reminder = new ReminderDTO();
        reminder.setReminder("Buy milk");
        reminder.setUserId(1L);

        mockMvc.perform(post("/api/reminders/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reminder)))
                .andExpect(status().isCreated());
    }
}
