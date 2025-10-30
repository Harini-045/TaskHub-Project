export interface TaskDTO {
    taskId: number;
    taskTitle: String;
  taskDescription: String;
  startDate: Date;
  dueDate: Date;
  priority: String;
  userId: number;   // ✅ not hardcoded or 0
  labelId: number;
}