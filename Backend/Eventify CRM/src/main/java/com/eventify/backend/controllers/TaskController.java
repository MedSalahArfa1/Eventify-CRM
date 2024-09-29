package com.eventify.backend.controllers;

import com.eventify.backend.dto.TaskDTO;
import com.eventify.backend.entities.EventEntity;
import com.eventify.backend.entities.TaskEntity;
import com.eventify.backend.repositories.EventRepository;
import com.eventify.backend.repositories.TaskRepository;
import com.eventify.backend.services.servicesInter.TaskServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskServiceInter taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> {
            TaskDTO dto = new TaskDTO();
            dto.setTaskId(task.getTaskId());
            dto.setTaskDescription(task.getTaskDescription());
            dto.setDeadline(task.getDeadline());
            dto.setAssignee(task.getAssignee());
            dto.setStatus(task.getStatus());
            dto.setEventId(task.getEvent().getEventId());

            // Fetch event name
            EventEntity event = eventRepository.findById(task.getEvent().getEventId()).orElse(null);
            dto.setEventName(event != null ? event.getEventName() : "Unknown Event");

            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        TaskEntity task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/event/{eventId}")
    public List<TaskEntity> getTasksByEvent(@PathVariable Long eventId) {
        return taskService.getTasksByEvent(eventId);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        if (task.getEvent() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        TaskEntity createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, @RequestBody TaskEntity taskDetails) {
        TaskEntity updatedTask = taskService.updateTask(id, taskDetails);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
