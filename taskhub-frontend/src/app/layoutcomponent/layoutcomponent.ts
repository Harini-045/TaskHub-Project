import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Sidebarcomponent } from "../sidebarcomponent/sidebarcomponent";
import { DashboardComponent } from "../dashboardcomponent/dashboardcomponent";

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterModule, Sidebarcomponent],
  templateUrl: './layoutcomponent.html',
  styleUrls: ['./layoutcomponent.css']
})
export class LayoutComponent {
  functionalities = [
    { name: 'Dashboard', icon: 'bi-speedometer2', route: '/dashboard' },
    { name: 'All Tasks', icon: 'bi-card-checklist', route: '/tasks' },
    { name: 'Projects', icon: 'bi-folder', route: '/project' },
    { name: 'Team Management', icon: 'bi-people', route: '/teams' },
    { name: 'User Management', icon: 'bi-person', route: '/users' },
    { name: 'Analytics', icon: 'bi-bar-chart', route: '/analytics' },
    { name: 'Settings', icon: 'bi-gear', route: '/settings' },
    { name: 'Logout', icon: 'bi-box-arrow-right', route: '/logout' }
  ];
  user = { name: 'Sarah Johnson', initials: 'SJ', role: 'admin' };
}
