package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.TaskEntity;

import java.util.List;

public interface TaskServiceInter {
    List<TaskEntity> getAllTasks();

    TaskEntity getTaskById(Long id);

    TaskEntity createTask(TaskEntity task);

    TaskEntity updateTask(Long id, TaskEntity taskDetails);

    void deleteTask(Long id);
}
