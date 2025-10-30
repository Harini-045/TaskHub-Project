import { Component } from '@angular/core';
import { Task } from '../../model/task.model';
import { Label } from '../../model/label.model';
import { User } from '../../model/user.model';
import { Project } from '../../model/project.model';
import { Taskservice } from '../../services/taskservice';
import { Labelservice } from '../../services/labelservice';
import { Authservice } from '../../services/authservice';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TaskDTO } from '../../model/taskdto.model';
import { Userservice } from '../../services/userservice';

@Component({
  selector: 'app-user-taskdashboard',
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './user-taskdashboard.html',
  styleUrl: './user-taskdashboard.css',
})
export class UserTaskdashboard {
  allTasks: Task[] = [];
  allLabels: Label[] = [];
  allUsers: User[] = [];
  User: User | null = null;

  selectedTask: Task | null = null;
  isEditMode: boolean = false;
  editTaskData: TaskDTO | null = null;

  // Add Task state
  addTaskMode: boolean = false;
  newTaskData: Partial<Task> = {};

  constructor(
    private taskService: Taskservice,
    private labelService: Labelservice,
    private authService: Authservice,
    private userService: Userservice,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadTasks();
    this.loadLabels();
    this.loadUsers();
  }

  // ✅ Load Users
  loadUsers() {
    this.userService.getAllUsers().subscribe({
      next: (response) => (this.allUsers = response),
      error: (err) => console.error('Error loading users:', err),
    });
  }

  // ✅ Load Tasks for Logged-in User
  loadTasks() {
    this.User = this.authService.retrieveUser();
    if (!this.User || this.User.userId === undefined) {
      console.error('User not found or userId missing.');
      return;
    }

    this.taskService.getAUserTask(this.User.userId).subscribe({
      next: (response) => {
        this.allTasks = response;
      },
      error: (err) => console.error('Error loading tasks:', err),
    });
  }

  // ✅ Load Labels
  loadLabels() {
    this.labelService.getAllLabels().subscribe({
      next: (response) => (this.allLabels = response),
      error: (err) => console.error('Error loading labels:', err),
    });
  }

  // ✅ Filter tasks by label (Kanban column)
  getColumnTasks(labelName: string): Task[] {
    return this.allTasks.filter(
      (task) =>
        task.label &&
        task.label.labelName?.toLowerCase() === labelName.toLowerCase()
    );
  }

  // ✅ Open selected task details
  openTask(task: Task) {
    this.selectedTask = { ...task };
    this.isEditMode = false;
    this.addTaskMode = false;
  }

  // ✅ Close modal
  closeTaskModal() {
    this.selectedTask = null;
    this.isEditMode = false;
    this.addTaskMode = false;
    this.editTaskData = null;
  }

  // ✅ Enable edit mode (convert Task → TaskDTO)
  editTask() {
    this.isEditMode = true;

    if (this.selectedTask) {
      this.editTaskData = {
        taskId: this.selectedTask.taskId,
        taskTitle: this.selectedTask.taskTitle,
        taskDescription: this.selectedTask.taskDescription,
        startDate: this.selectedTask.startDate,
        dueDate: this.selectedTask.dueDate,
        priority: this.selectedTask.priority,
        userId: this.selectedTask.user?.userId ?? 0,
        labelId: this.selectedTask.label?.labelId ?? 0,
      };
    }
  }

  // ✅ Delete a task
  deleteTask() {
    if (this.selectedTask) {
      this.taskService.deleteATask(this.selectedTask.taskId).subscribe({
        next: () => {
          console.log('Task deleted');
          this.loadTasks();
          this.selectedTask = null;
        },
        error: (err) => console.error('Error deleting task:', err),
      });
    }
  }

  // ✅ Save edited task
  saveTask() {
    if (this.editTaskData) {
      const updatedTask: TaskDTO = {
        taskId: this.editTaskData.taskId,
        taskTitle: this.editTaskData.taskTitle,
        taskDescription: this.editTaskData.taskDescription,
        startDate: this.editTaskData.startDate,
        dueDate: this.editTaskData.dueDate,
        priority: this.editTaskData.priority,
        userId: this.editTaskData.userId,
        labelId: this.editTaskData.labelId,
      };

      console.log('Update Payload:', updatedTask);

      this.taskService.updateATask(updatedTask).subscribe({
        next: () => {
          console.log('Task updated successfully');
          this.loadTasks();
          this.isEditMode = false;
          this.selectedTask = null;
        },
        error: (err) => console.error('Error updating task:', err),
      });
    }
  }

  // ✅ Open Add Task modal
  openAddTaskCard() {
    this.addTaskMode = true;
    this.selectedTask = null;
    const now = new Date();

    this.newTaskData = {
      project: {} as Project,
      user: this.User ?? ({} as User),
      label: this.allLabels.length ? this.allLabels[0] : ({} as Label),
      priority: 'Medium',
      startDate: now,
      dueDate: undefined,
      createdAt: now,
      updatedAt: now,
    };
  }

  // ✅ Close Add Task modal
  closeAddTaskCard() {
    this.addTaskMode = false;
    this.newTaskData = {};
  }

  // ✅ Save newly added task
  saveNewTask() {
    if (
      !this.newTaskData.taskTitle ||
      !this.newTaskData.taskDescription ||
      !this.newTaskData.dueDate ||
      !this.newTaskData.user ||
      !this.newTaskData.label
    ) {
      alert('Please fill in all required fields.');
      return;
    }

    const now = new Date();

    const newTask: Task = {
      taskId: 0,
      project: this.newTaskData.project ?? ({} as Project),
      taskTitle: this.newTaskData.taskTitle,
      taskDescription: this.newTaskData.taskDescription,
      user: this.newTaskData.user,
      priority: this.newTaskData.priority || 'Medium',
      startDate: now,
      dueDate: new Date(this.newTaskData.dueDate),
      createdAt: now,
      updatedAt: now,
      label: this.newTaskData.label,
    };

    console.log('Add Task Payload:', newTask);

    this.taskService.addATask(newTask).subscribe({
      next: () => {
        console.log('Task added successfully');
        this.loadTasks();
        this.closeAddTaskCard();
      },
      error: (err) => console.error('Error adding task:', err),
    });
  }
}
