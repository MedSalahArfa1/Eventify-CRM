export interface Task {
  taskId: number | null;
  taskDescription: string;
  deadline: Date;
  assignee: string;
  status: number;
  event: { eventId: number };
}
