import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface StatCard {
  label: string;
  value: string;
  icon: string;
  color: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboardcomponent.html',
  styleUrls: ['./dashboardcomponent.css']
})
export class DashboardComponent {
  statCards: StatCard[] = [
    { label: 'Total Tasks', value: '10', icon: 'bi-graph-up', color: 'primary' },
    { label: 'Active Projects', value: '5', icon: 'bi-folder', color: 'success' },
    { label: 'Team Members', value: '5', icon: 'bi-people', color: 'info' },
    { label: 'Completion Rate', value: '10%', icon: 'bi-graph-up', color: 'warning' },
    { label: 'Completed', value: '1', icon: 'bi-check-circle', color: 'success' },
    { label: 'In Progress', value: '4', icon: 'bi-arrow-repeat', color: 'primary' },
    { label: 'To Do', value: '3', icon: 'bi-clock', color: 'secondary' },
    { label: 'Overdue', value: '1', icon: 'bi-exclamation-circle', color: 'danger' }
  ];
}
