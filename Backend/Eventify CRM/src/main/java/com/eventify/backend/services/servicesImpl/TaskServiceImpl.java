package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.TaskEntity;
import com.eventify.backend.repositories.EventRepository;
import com.eventify.backend.repositories.TaskRepository;
import com.eventify.backend.services.servicesInter.TaskServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskServiceInter {

    private final TaskRepository taskRepository;
    private final EventRepository eventRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, EventRepository eventRepository) {
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<TaskEntity> getTasksByEvent(Long eventId) {
        return taskRepository.findByEvent_EventId(eventId);
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        if (task.getEvent() == null || !eventRepository.existsById(task.getEvent().getEventId())) {
            throw new IllegalArgumentException("Event is either null or does not exist");
        }
        task.setStatus(0); // Default status
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity updateTask(Long id, TaskEntity taskDetails) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            if (taskDetails.getTaskDescription() != null) {
                task.setTaskDescription(taskDetails.getTaskDescription());
            }
            if (taskDetails.getDeadline() != null) {
                task.setDeadline(taskDetails.getDeadline());
            }
            if (taskDetails.getAssignee() != null) {
                task.setAssignee(taskDetails.getAssignee());
            }
            if (taskDetails.getEvent() != null && eventRepository.existsById(taskDetails.getEvent().getEventId())) {
                task.setEvent(taskDetails.getEvent());
            } else if (taskDetails.getEvent() != null) {
                throw new IllegalArgumentException("Event does not exist");
            }
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
