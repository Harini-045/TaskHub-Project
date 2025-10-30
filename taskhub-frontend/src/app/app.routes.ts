import { Routes } from '@angular/router';
import { Login } from './login/login';
import { DashboardComponent } from './dashboardcomponent/dashboardcomponent';
import { LayoutComponent } from './layoutcomponent/layoutcomponent';
import { Logoutcomponent } from './logoutcomponent/logoutcomponent';
import { Registercomponent } from './registercomponent/registercomponent';
import { AdminTaskdashboard } from './task-dashboard/admin-taskdashboard/admin-taskdashboard';
import { UserTaskdashboard } from './task-dashboard/user-taskdashboard/user-taskdashboard';
import { Teamdashboard } from './teamdashboard/teamdashboard';
import { Userdashboard } from './userdashboard/userdashboard';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: Login },
    { path: 'signup', component: Registercomponent},
    {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'admin-task', component: AdminTaskdashboard},
      { path: 'user-task', component: UserTaskdashboard},
      { path: 'team', component: Teamdashboard},
      { path: 'user', component: Userdashboard}
    ]
  },
   { path: 'logout', component: Logoutcomponent},
];
