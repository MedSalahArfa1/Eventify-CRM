// tasks.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from 'app/models/task.model';
import { Event } from 'app/models/event.model'; 
import { TaskService } from 'app/services/task.service'; 
import { AuthService } from 'app/services/auth.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  events: Event[] = [];
  currentTask: Task | null = null;
  isAddingTask: boolean = false;
  formattedDeadline: string = '';
  searchQuery: string = '';

  constructor(
    private tasksService: TaskService, 
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadTasks();
    this.loadEvents();
  }

  loadTasks(): void {
      this.tasksService.getTasks().subscribe(tasks => {
        this.tasks = this.sortTasksByDeadline(tasks);
        this.filterTasks(); 
      });
  }

  loadEvents(): void {
    this.tasksService.getEvents().subscribe(events => {
      this.events = events;
      console.log('Loaded Events:', this.events);
      if (this.currentTask && this.currentTask.event) {
        console.log('Current Task Event ID:', this.currentTask.event.eventId);
      }
    });
  }

  sortTasksByDeadline(tasks: Task[]): Task[] {
    return tasks.sort((a, b) => {
      if (a.deadline < b.deadline) return -1;  
      if (a.deadline > b.deadline) return 1;
      return 0;
    });
  }

  filterTasks(): void {
    if (!this.searchQuery.trim()) {
      this.filteredTasks = this.tasks;
    } else {
      const query = this.searchQuery.toLowerCase();
      this.filteredTasks = this.tasks.filter(task => {
        const eventName = task.event ? this.getEventName(task.event.eventId) : 'Unknown Event';
        return eventName.toLowerCase().includes(query);
      });
    }
  }

  getEventName(eventId: number): string {
    const event = this.events.find(event => event.eventId === eventId);
    return event ? event.eventName : 'Unknown Event';
  }

  openAddTaskCard(): void {
    this.currentTask = {
      taskId: null,
      taskDescription: '',
      deadline: new Date(),
      assignee: '',
      status: 0,
      event: { eventId: this.events.length > 0 ? this.events[0].eventId : 0 }  
    };
    this.isAddingTask = true;
    this.formattedDeadline = this.formatDateTime(new Date());
  }

  openEditTaskCard(task: Task): void {
    if (task) {
      this.currentTask = {
        taskId: task.taskId,
        taskDescription: task.taskDescription,
        deadline: new Date(task.deadline),
        assignee: task.assignee,
        status: task.status,
        event: task.event ? { eventId: task.event.eventId } : { eventId: 0 }
      };
      console.log('Editing Task:', this.currentTask);
      
      this.isAddingTask = false;
      this.formattedDeadline = this.formatDateTime(this.currentTask.deadline);
    } else {
      console.error('Task to edit is undefined');
    }
  }
  
  addTask(): void {
    if (this.currentTask && this.currentTask.event) {
      this.currentTask.event = { eventId: this.currentTask.event.eventId };

      this.tasksService.createTask(this.currentTask).subscribe(() => {
        this.resetForm();
        this.loadTasks();  
      }, error => {
        console.error('Error creating task:', error);
      });
    } else {
      console.error('Current task or event is undefined');
    }
  }

  updateTask(): void {
    if (this.currentTask && this.currentTask.taskId) {
      const updatedTask = {
        ...this.currentTask,
        event: { eventId: this.currentTask.event.eventId }
      };
  
      this.tasksService.updateTask(this.currentTask.taskId, updatedTask).subscribe(() => {
        this.resetForm();
        this.loadTasks();  
      }, error => {
        console.error('Error updating task:', error);
      });
    } else {
      console.error('Current task is undefined or missing taskId');
    }
  }

  deleteTask(taskId: number | null): void {
    if (taskId !== null) {
      this.tasksService.deleteTask(taskId).subscribe(() => {
        this.loadTasks();  
      }, error => {
        console.error('Error deleting task:', error);
      });
    }
  }

  resetForm(): void {
    this.currentTask = null;
    this.isAddingTask = false;
    this.formattedDeadline = '';
  }

  handleDeadlineChange(event: any): void {
    if (this.currentTask) {
      this.currentTask.deadline = new Date(event.target.value);
    }
  }

  formatDateTime(date: Date): string {
    const d = new Date(date);
    const month = ('0' + (d.getMonth() + 1)).slice(-2);
    const day = ('0' + d.getDate()).slice(-2);
    const year = d.getFullYear();
    const hour = ('0' + d.getHours()).slice(-2);
    const minute = ('0' + d.getMinutes()).slice(-2);
    return `${year}-${month}-${day}T${hour}:${minute}`;
  }

  toggleStatus(task: Task): void {
    if (task.taskId !== null) {
      task.status = task.status === 1 ? 0 : 1;
      this.tasksService.updateTask(task.taskId, task).subscribe();
    }
  }

  isOverdue(task: Task): boolean {
    const now = new Date();
    return task.status === 0 && task.deadline < now;
  }

  // Getter for authService
  get isEventManager(): boolean {
    return this.authService.hasAnyRole([2]);
  }
}
