package com.uniamerica.reminder.dto;

import lombok.Data;

@Data
public class ReminderDTO {
    private Long id;
    private String reminder;
    private Long userId;
}