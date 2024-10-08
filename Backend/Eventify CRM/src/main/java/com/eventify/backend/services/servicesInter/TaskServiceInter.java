package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInter {
    List<TaskEntity> getAllTasks();
    TaskEntity getTaskById(Long taskId);
    List<TaskEntity> getTasksByEvent(Long eventId);
    TaskEntity createTask(TaskEntity task);
    TaskEntity updateTask(Long id, TaskEntity taskDetails);
    void deleteTask(Long id);
}

