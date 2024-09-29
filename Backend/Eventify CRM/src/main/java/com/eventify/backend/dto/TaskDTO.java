package com.eventify.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long taskId;
    private String taskDescription;
    private Date deadline;
    private String assignee;
    private Integer status=0;
    private Long eventId;
    private String eventName;

}