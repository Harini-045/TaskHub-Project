import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Task } from '../../model/task.model';
import { Label } from '../../model/label.model';
import { Taskservice } from '../../services/taskservice';
import { Labelservice } from '../../services/labelservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-taskdashboard',
  imports: [FormsModule, CommonModule],
  templateUrl: './admin-taskdashboard.html',
  styleUrl: './admin-taskdashboard.css',
})
export class AdminTaskdashboard {
  allTasks: Task[] = [];
  allLabels: Label[] = [];
  selectedTask: Task | null = null;
  isEditMode: boolean = false;
  editTaskData: Task | null = null;

  constructor(
    private taskService: Taskservice,
    private labelService: Labelservice,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadTasks();
    this.loadLabels();
  }

  loadTasks() {
    this.taskService.getAllTasks().subscribe({
      next: (response) => {
        console.log(response);
        this.allTasks = response;
      },
      error: (err) => console.error('Error loading tasks:', err)
    });
  }

  loadLabels() {
    this.labelService.getAllLabels().subscribe({
      next: (response) => {
          console.log(response);
        this.allLabels = response;
      },
      error: (err) => console.error('Error loading labels:', err)
    });
  }

  getColumnTasks(labelName: string) {
    return this.allTasks.filter(task => task.label.labelName === labelName);
  }

  openTask(task: Task) {
    this.selectedTask = { ...task };
    this.isEditMode = false;
  }

  closeTaskModal() {
    this.selectedTask = null;
    this.isEditMode = false;
    this.editTaskData = null;
  }

  editTask() {
    this.isEditMode = true;
    if (this.selectedTask) {
      this.editTaskData = { ...this.selectedTask };
    }
  }

  // saveTask() {
  //   if (this.editTaskData) {
  //     this.taskService.updateTask(this.editTaskData).subscribe(() => {
  //       this.loadTasks();
  //       this.isEditMode = false;
  //       this.selectedTask = null;
  //     });
  //   }
  // }

  deleteTask() {
    if (this.selectedTask) {
      this.taskService.deleteATask(this.selectedTask.taskId).subscribe(() => {
        this.loadTasks();
        this.selectedTask = null;
      });
    }
  }
}
