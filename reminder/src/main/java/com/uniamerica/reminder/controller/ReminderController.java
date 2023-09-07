package com.uniamerica.reminder.controller;

import com.uniamerica.reminder.dto.ReminderDTO;
import com.uniamerica.reminder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> getAllReminders() {
        List<ReminderDTO> reminders = reminderService.findAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReminderDTO> createReminder(@RequestBody ReminderDTO reminderDto) {
        ReminderDTO createdReminder = reminderService.save(reminderDto);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }
}
