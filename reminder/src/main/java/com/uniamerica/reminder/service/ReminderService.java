package com.uniamerica.reminder.service;

import com.uniamerica.reminder.entity.Reminder;
import com.uniamerica.reminder.repository.ReminderRepository;
import com.uniamerica.reminder.dto.ReminderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public List<ReminderDTO> findAll() {
        return reminderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReminderDTO save(ReminderDTO reminderDto) {
        Reminder reminder = new Reminder();
        reminder.setReminder(reminderDto.getReminder());

        reminder = reminderRepository.save(reminder);

        return convertToDTO(reminder);
    }

    private ReminderDTO convertToDTO(Reminder reminder) {
        ReminderDTO dto = new ReminderDTO();
        dto.setId(reminder.getId());
        dto.setReminder(reminder.getReminder());
        if (reminder.getUser() != null) {
            dto.setUserId(reminder.getUser().getId());
        }
        return dto;
    }
}
