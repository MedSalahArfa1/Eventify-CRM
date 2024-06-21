package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.TaskEntity;
import com.eventify.backend.repositories.TaskRepository;
import com.eventify.backend.services.servicesInter.TaskServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskServiceInter {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity updateTask(Long id, TaskEntity taskDetails) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTaskName(taskDetails.getTaskName());
            task.setTaskDescription(taskDetails.getTaskDescription());
            task.setDeadline(taskDetails.getDeadline());
            task.setStatus(taskDetails.getStatus());
            task.setEvent(taskDetails.getEvent());
            task.setAssignedBy(taskDetails.getAssignedBy());
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
